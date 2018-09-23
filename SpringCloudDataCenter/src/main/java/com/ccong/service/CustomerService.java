package com.ccong.service;

import com.ccong.jpa.dao.repository.CustomerRepository;
import com.ccong.jpa.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer findOneByUsername(String name) {
        return customerRepository.findOneByUsername(name);
    }

    public Customer findOne(Long id) {
        return customerRepository.getOne(id);
    }

    public int charge(Long customerId, int amount) {
        return customerRepository.charge(customerId,amount);
    }
}
