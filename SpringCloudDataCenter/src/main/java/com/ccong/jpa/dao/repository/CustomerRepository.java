package com.ccong.jpa.dao.repository;

import com.ccong.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);

    //keep operation safe
    @Modifying
    @Query("UPDATE customer SET deposit = deposit - ?2 WHERE id = ?1 and deposit > ?2")
    int charge(Long customerId, int amount);
}
