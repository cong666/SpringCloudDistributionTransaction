package com.ccong.web.controller;

import com.ccong.feign.OrderClient;
import com.ccong.jpa.dao.repository.CustomerRepository;
import com.ccong.jpa.domain.Customer;
import com.ccong.jpa.domain.Order;
import com.ccong.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    request with http://localhost:8083/api/customer
 or with proxy we need to add the proxy id 'user' from 8888
    : http://localhost:8888/user/api/customer
*/
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderClient orderClient;

    /*
      {
        "username":"cc",
        "password":"cc",
        "role" : "manager"
      }
     */
    @PostMapping("")
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    //@RequestMapping(value = "/customer/", method = RequestMethod.GET)
    @GetMapping("")
    public List<Customer> getAll() {
        return customerService.findAllCustomer();
    }

    /*
    request with http://localhost:8083/api/customer/order
         or with proxy : http://localhost:8888/user/api/customer/order
    */
    @GetMapping("/order")
    public Map getMyInfo() {
        Customer customer = customerService.findOneByUsername("cc");
        Order order = orderClient.getMyOrder(1L);
        Map result = new HashMap();
        result.put("customer", customer);
        result.put("order", order);
        return result;
    }
}
