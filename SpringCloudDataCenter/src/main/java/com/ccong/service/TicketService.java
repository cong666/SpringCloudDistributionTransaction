package com.ccong.service;

import com.ccong.dto.OrderDTO;
import com.ccong.jpa.dao.repository.TicketRepository;
import com.ccong.jpa.domain.Ticket;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    public static Logger log  = LoggerFactory.getLogger(TicketService.class);
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findOne(Long id) {
        return ticketRepository.findById(id).get();
    }

    public Ticket findOneByTicketNumber(String num) {
        return ticketRepository.findOneByTicketNumber(num);
    }

    @Transactional
    public Ticket lockTicket(OrderDTO orderDTO) {
        log.info("begin to lock the ticket");
        Ticket ticket = this.findOneByTicketNumber(orderDTO.getTicketNumber());
        ticket.setLockUser(orderDTO.getCustomerId());
        ticket = this.save(ticket);
        log.info("wait 10 second before committing the ticket");
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("lock ticket finished");
        return ticket;
    }

    @Transactional
    public int lockTicketCurrency(OrderDTO orderDTO) {
        int lockCount = ticketRepository.lockTicket(orderDTO.getCustomerId(),orderDTO.getTicketNumber());
        log.info("locket ticket currency, lock count : "+lockCount);
        log.info("lock ticket finished");
        return lockCount;
    }

    @Transactional
    public int submitTicket(OrderDTO orderDTO) {
        int submitCount = ticketRepository.submitTicket(orderDTO.getCustomerId(),orderDTO.getTicketNumber());
        log.info("submit ticket count : "+submitCount);
        return submitCount;
    }

    @Transactional
    public int unLockTicket(OrderDTO orderDTO) {
        return ticketRepository.unLockTicket(orderDTO.getCustomerId(),orderDTO.getTicketNumber());
    }

    @Transactional
    public int unsubmitTicket(OrderDTO orderDTO) {
        return ticketRepository.unsubmitTicket(orderDTO.getCustomerId(),orderDTO.getTicketNumber());
    }
}
