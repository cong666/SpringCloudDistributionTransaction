package com.ccong.service;

import com.ccong.jpa.dao.repository.OrderRepository;
import com.ccong.jpa.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order findOrderByCustomerId(Long id) {
        return orderRepository.findOneByCustomerId(id);
    }

    public Order findOrderByUuid(String id) {
        return orderRepository.findOneByUuid(id);
    }
}
