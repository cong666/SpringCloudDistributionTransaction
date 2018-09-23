package com.ccong.jms;

import com.ccong.app.config.UserMQConfig;
import com.ccong.dto.OrderDTO;
import com.ccong.jpa.dao.repository.CustomerRepository;
import com.ccong.jpa.domain.Customer;
import com.ccong.jpa.domain.PayInfo;
import com.ccong.service.CustomerService;
import com.ccong.service.PayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by: ccong
 * Date: 18/9/23 上午11:51
 */
@Service
public class UserMQReceiver {

    private static Logger log = LoggerFactory.getLogger(UserMQReceiver.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PayInfoService payInfoService;
    @Autowired
    private UserMQSender userMQSender;

    @Transactional
    @RabbitListener(queues= UserMQConfig.ORDER_PAY)
    public void payOrder(OrderDTO orderDTO) {
        log.info("Pay Order:" + orderDTO);
        //check deposit
        Customer customer = customerService.findOne(orderDTO.getCustomerId());
        //check if the order is already payed, this method may be retried multiple times if failed.
        PayInfo payInfo = payInfoService.findOneByOrderId(orderDTO.getId());
        if(payInfo!=null) {
            log.info("order is already payed");
        } else {
            //do the charge if not paid
            int chargedCount = customerService.charge(orderDTO.getCustomerId(),orderDTO.getAmount());
            if(chargedCount==1) {
                //create new payinfo
                PayInfo pi = new PayInfo();
                pi.setAmount(orderDTO.getAmount());
                pi.setCustomerId(orderDTO.getCustomerId());
                pi.setOrderId(orderDTO.getId());
                pi.setStatus("PAID");
                payInfoService.save(pi);
                log.info("charged successful:");
                //change the status every time when the method is called
                //change orderDTO status and continue to transfer the message to next hanlder
                orderDTO.setStatus("PAID");
                //send to 'order:ticket_move' to submit the ticket
                userMQSender.ticketSubmit(orderDTO);

            } else {
                orderDTO.setErrorReason("Pay Error");
                orderDTO.setStatus("PAY_ERROR");
                //send message to "order:unlock" -> TicketMQReceiver to unlock the ticket
                userMQSender.unlockTicket(orderDTO);

                log.info("charged failed - order:"+orderDTO+", customer:"+customer);
                return;
            }
        }
        //if order is already paid
        orderDTO.setStatus("PAID");
        //send to 'order:ticket_move' to submit the ticket
        userMQSender.ticketSubmit(orderDTO);
    }
}
