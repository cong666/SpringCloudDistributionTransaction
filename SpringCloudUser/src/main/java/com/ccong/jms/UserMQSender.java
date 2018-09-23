package com.ccong.jms;

import com.ccong.app.config.UserMQConfig;
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
public class UserMQSender {
    private static Logger log = LoggerFactory.getLogger(UserMQSender.class);


    @Autowired
    AmqpTemplate amqpTemplate;

    public void ticketSubmit(OrderDTO orderDTO) {
        log.info("submit ticket:"+orderDTO);
        amqpTemplate.convertAndSend(UserMQConfig.ORDER_TICKET_MOVE, orderDTO);
    }

    public void unlockTicket(OrderDTO orderDTO) {
        log.info("unlock ticket:"+orderDTO);
        amqpTemplate.convertAndSend(UserMQConfig.ORDER_UNLOCK, orderDTO);
    }

}
