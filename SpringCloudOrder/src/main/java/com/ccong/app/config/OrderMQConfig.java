package com.ccong.app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by: ccong
 * Date: 18/9/23 下午2:07
 */
@Service
public class OrderMQConfig {
    public static final String NEW_ORDER_QUEUE = "order:new";
    public static final String ORDER_LOCKED = "order:locked";
    public static final String ORDER_PAY = "order:pay";
    public static final String ORDER_TICKET_MOVE = "order:ticket_move";
    public static final String ORDER_FINISHED = "order:finished";
    public static final String ORDER_FAILED = "order:failed";
    public static final String ORDER_UNLOCK = "order:unlock";

    @Bean
    public Queue newOrderQueue() {
        return new Queue(NEW_ORDER_QUEUE,true);
    }

    @Bean
    public Queue orderLockedQueue() {
        return new Queue(ORDER_LOCKED,true);
    }

    @Bean
    public Queue orderPayQueue() {
        return new Queue(ORDER_PAY,true);
    }

    @Bean
    public Queue orderTicketSubmit() {
        return new Queue(ORDER_TICKET_MOVE,true);
    }

    @Bean
    public Queue orderFinished() {
        return new Queue(ORDER_FINISHED,true);
    }

    @Bean
    public Queue orderFailed() {
        return new Queue(ORDER_FAILED,true);
    }
    @Bean
    public Queue orderUnlock() {
        return new Queue(ORDER_UNLOCK,true);
    }
}
