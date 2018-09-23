package com.ccong.jpa.dao.repository;

import com.ccong.jpa.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findOneByTicketNumber(String ticketNumber);

    //use DB lock
    @Transactional
    @Modifying
    @Query("UPDATE Ticket SET lockUser = ?1 WHERE lockUser is NULL and ticketNumber = ?2")
    int lockTicket(Long customerId, String ticketNum);

    @Modifying
    @Query("UPDATE Ticket SET lockUser = null WHERE lockUser = ?1 and ticketNumber = ?2")
    int unLockTicket(Long customerId, String ticketNum);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket SET owner=?1 , lockUser = NULL WHERE lockUser=?1 and ticketNumber = ?2")
    int submitTicket(Long customerId, String ticketNum);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket SET owner = NULL WHERE owner = ?1 and ticketNumber = ?2")
    int unsubmitTicket(Long customerId, String ticketNum);


}
