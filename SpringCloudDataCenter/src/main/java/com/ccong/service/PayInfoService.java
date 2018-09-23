package com.ccong.service;

import com.ccong.jpa.dao.repository.OrderRepository;
import com.ccong.jpa.dao.repository.PayInfoRepository;
import com.ccong.jpa.domain.Order;
import com.ccong.jpa.domain.PayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PayInfoService {
    @Autowired
    private PayInfoRepository payInfoRepository;

    public PayInfo save(PayInfo pi) {
        return payInfoRepository.save(pi);
    }

    public List<PayInfo> findAll() {
        return payInfoRepository.findAll();
    }

    public PayInfo findOne(Long id) {
        return payInfoRepository.findById(id).get();
    }

    public PayInfo findOneByOrderId(Long id) {
        return payInfoRepository.findOneByOrderId(id);
    }
}
