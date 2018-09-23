package com.ccong.jms;

import com.ccong.app.config.TicketMQConfig;
import com.ccong.dto.OrderDTO;
import com.ccong.service.TicketService;
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
public class TicketMQReceiver {

    private static Logger log = LoggerFactory.getLogger(TicketMQReceiver.class);

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketMQSender ticketMQSender;

    @Transactional
    @RabbitListener(queues= TicketMQConfig.NEW_ORDER_QUEUE)
    public void handleTicketLock(OrderDTO orderDTO) {
        log.info("new order message received -> handleTicketLock:" + orderDTO);
        int lockCount = ticketService.lockTicketCurrency(orderDTO);
        if(lockCount == 1) {
            orderDTO.setStatus("TICKET_LOCKED");
            ticketMQSender.orderLocked(orderDTO);
        } else {
            //if order is failed, just send it to the failed queue to be processed later
            orderDTO.setStatus("ORDER_FAILED");
            ticketMQSender.orderFailed(orderDTO);
        }
    }

    @Transactional
    @RabbitListener(queues= TicketMQConfig.ORDER_TICKET_MOVE)
    public void submitTicket(OrderDTO orderDTO) {
        log.info("submit ticket -> submitTicket:" + orderDTO);
        //set owner and remove lockUser for ticket
        int submitCount = ticketService.submitTicket(orderDTO);

        if(submitCount==0) {
            log.info("submit ticket failed");
        }

        orderDTO.setStatus("TICKET_SUBMITTED");
        //order finished
        ticketMQSender.orderFinished(orderDTO);
    }

    @Transactional
    @RabbitListener(queues= TicketMQConfig.ORDER_UNLOCK)
    public void unlockTicket(OrderDTO orderDTO) {
        log.info("unlock ticket:" + orderDTO);
        int count = ticketService.unLockTicket(orderDTO);
        if(count==1) {
            log.info("unlock finished successful");
        } else {
            log.info("ticket is already unlocked");
        }
        ticketMQSender.orderFailed(orderDTO);
    }

}
