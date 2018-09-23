package com.ccong.app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by: ccong
 * Date: 18/9/23 下午2:07
 */
@Service
public class UserMQConfig {
    public static final String ORDER_PAY = "order:pay";
    public static final String ORDER_TICKET_MOVE = "order:ticket_move";
    public static final String ORDER_UNLOCK = "order:unlock";

    /*
    @Bean
    public Queue newUserQueue() {
        return new Queue(ORDER_PAY,true);
    }
    */
}
