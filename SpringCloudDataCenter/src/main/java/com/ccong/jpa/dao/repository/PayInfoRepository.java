package com.ccong.jpa.dao.repository;

import com.ccong.jpa.domain.Order;
import com.ccong.jpa.domain.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
    PayInfo findOneByOrderId(Long id);
}
