package com.ccong.app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: ccong
 * Date: 18/9/23 上午11:43
 */
@Configuration
public class TicketMQConfig {
    public static final String LOCK_TICKET = "ticket:lock";
    public static final String NEW_ORDER_QUEUE = "order:new";
    //create order if received
    public static final String ORDER_LOCKED = "order:locked";
    public static final String ORDER_TICKET_MOVE = "order:ticket_move";
    public static final String ORDER_FINISHED = "order:finished";
    public static final String ORDER_FAILED = "order:failed";
    public static final String ORDER_UNLOCK = "order:unlock";

    @Bean
    public Queue newQueue() {
        return new Queue(LOCK_TICKET,true);
    }
}
