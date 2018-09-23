package com.ccong.jms;

import com.ccong.app.config.OrderMQConfig;
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
public class OrderMQSender {
    private static Logger log = LoggerFactory.getLogger(OrderMQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;
    public void newOrder(OrderDTO orderDTO) {
        log.info("order:new "+orderDTO);
        amqpTemplate.convertAndSend(OrderMQConfig.NEW_ORDER_QUEUE, orderDTO);
    }

    public void payOrder(OrderDTO orderDTO) {
        log.info("order:pay "+orderDTO);
        amqpTemplate.convertAndSend(OrderMQConfig.ORDER_PAY, orderDTO);
    }
}
