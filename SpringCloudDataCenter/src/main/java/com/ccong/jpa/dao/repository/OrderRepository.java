package com.ccong.jpa.dao.repository;

import com.ccong.jpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findOneByCustomerId(Long customerId);
    public Order findOneByUuid(String uuid);
}
