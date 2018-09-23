package com.ccong.web.controller;

import com.ccong.dto.OrderDTO;
import com.ccong.jms.TicketMQReceiver;
import com.ccong.jms.TicketMQSender;
import com.ccong.jpa.domain.Ticket;
import com.ccong.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ticket")
public class TicketResource {
    private static Logger log = LoggerFactory.getLogger(TicketResource.class);

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketMQSender mqSender;
    @Autowired
    private TicketMQReceiver ticketMQReceiver;
    @Autowired
    private TicketMQSender ticketMQSender;

    @PostMapping("")
    public Ticket create(@RequestBody Ticket t) {
        Ticket ticket = ticketService.save(t);
        return ticket;
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        Ticket t = ticketService.findOne(id);
        return t;
    }

    @GetMapping("")
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

    @PostMapping("/lock")
    public Ticket lockTicket(@RequestBody OrderDTO orderDTO) {

        //mqSender.newOrderCreated(orderDTO);
        Ticket ticket = ticketService.lockTicket(orderDTO);
        return ticket;
    }

    @PostMapping("/lockcurrency")
    public int lockTicketCurrency(@RequestBody OrderDTO orderDTO) {
        int lockCount = ticketService.lockTicketCurrency(orderDTO);
        if(lockCount==1) {
            orderDTO.setStatus("TICKET_LOCKED");
            //Create the order if ticket is locked successful!
            //send new message to create the order
            ticketMQSender.orderLocked(orderDTO);
        } else {
            log.info("order failed to lock the ticket:"+orderDTO);
        }
        return lockCount;
    }
}
