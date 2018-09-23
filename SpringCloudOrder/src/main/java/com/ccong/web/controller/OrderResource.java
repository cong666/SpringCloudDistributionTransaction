package com.ccong.web.controller;

import com.ccong.dto.OrderDTO;
import com.ccong.jms.OrderMQSender;
import com.ccong.jpa.domain.Order;
import com.ccong.service.OrderService;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderResource {
    Logger log = LoggerFactory.getLogger(OrderResource.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMQSender orderMqSender;

    private TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator();

    @PostMapping("")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderDTO.setUuid(uuidGenerator.generate().toString());
        orderMqSender.newOrder(orderDTO);
        log.info("'new:order' message sent");
    }

    @GetMapping("/{id}")
    public Order getMyOrder(@PathVariable Long id) {
        Order order = orderService.findOne(id);
        return order;
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderService.findAll();
    }
}
