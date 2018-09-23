package com.ccong.jms;

import com.ccong.app.config.TicketMQConfig;
import com.ccong.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by: ccong
 * Date: 18/9/23 上午11:51
 */
@Service
public class TicketMQSender {
    private static Logger log = LoggerFactory.getLogger(TicketMQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void newOrderCreated(OrderDTO orderDTO) {
        String msg = "New Order Created";
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(TicketMQConfig.LOCK_TICKET, orderDTO);
    }

    public void orderLocked(OrderDTO orderDTO) {
        log.info("orderLocked:"+orderDTO);
        amqpTemplate.convertAndSend(TicketMQConfig.ORDER_LOCKED, orderDTO);
    }

    public void orderFinished(OrderDTO orderDTO) {
        log.info("order finished : "+orderDTO);
        amqpTemplate.convertAndSend(TicketMQConfig.ORDER_FINISHED,orderDTO);
    }
    public void orderFailed(OrderDTO orderDTO) {
        log.info("order failed : "+orderDTO);
        amqpTemplate.convertAndSend(TicketMQConfig.ORDER_FAILED,orderDTO);
    }
}
