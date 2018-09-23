package com.ccong.feign;

import com.ccong.jpa.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/*
* Call this feign interface from CustomerResource
* */
@Component
@FeignClient(value = "order", path = "/api/order")
public interface OrderClient {
    @GetMapping("/{id}")
    Order getMyOrder(@PathVariable(name = "id") Long id);

    @PostMapping("")
    Order create(@RequestBody Order order);
}
