package com.ccong.jms;

import com.ccong.app.config.OrderMQConfig;
import com.ccong.dto.OrderDTO;
import com.ccong.jpa.domain.Order;
import com.ccong.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

/**
 * Created by: ccong
 * Date: 18/9/23 上午11:51
 */
@Service
public class OrderMQReceiver {

    private static Logger log = LoggerFactory.getLogger(OrderMQReceiver.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMQSender orderMqSender;

    @Transactional
    @RabbitListener(queues= OrderMQConfig.ORDER_LOCKED)
    public void createOrder(OrderDTO orderDTO) {
        log.info("createOrder form message :" + orderDTO);
        if(orderService.findOrderByUuid(orderDTO.getUuid())!=null) {
            log.info("Order is already processed");
        } else {
            Order order = newOrder(orderDTO);
            order = orderService.save(order);
            //set the id for OrderDTO
            orderDTO.setId(order.getId());
            log.info("Order is created successful");
        }
        //change the orderDTO status = continue to transfer the orderDTO to 'order:pay' message queue
        orderDTO.setStatus("NEW");
        //send message to pay order
        orderMqSender.payOrder(orderDTO);

    }
    private Order newOrder(OrderDTO dto) {
        Order order = new Order();
        order.setUuid(dto.getUuid());
        order.setAmount(dto.getAmount());
        order.setTitle(dto.getTitle());
        order.setCustomerId(dto.getCustomerId());
        order.setTicketNumber(dto.getTicketNumber());
        order.setStatus("NEW");
        order.setCreatedTime(ZonedDateTime.now());
        return order;
    }

    @Transactional
    @RabbitListener(queues= OrderMQConfig.ORDER_FINISHED)
    public void orderFinished(OrderDTO orderDTO) {
        log.info("Order finished :"+orderDTO);
        Order order = orderService.findOne(orderDTO.getId());
        order.setStatus("FINISHED");
        orderService.save(order);
    }

    @Transactional
    @RabbitListener(queues= OrderMQConfig.ORDER_FAILED)
    public void orderFailed(OrderDTO orderDTO) {
        log.info("Order failed :"+orderDTO);
        Order order = orderService.findOne(orderDTO.getId());
        if(order==null) {
            order = newOrder(orderDTO);
            order.setErrorReason("Failed to lock ticket");
        }
        order.setErrorReason(orderDTO.getErrorReason());
        order.setStatus("FAILED");
        orderService.save(order);
    }

}
