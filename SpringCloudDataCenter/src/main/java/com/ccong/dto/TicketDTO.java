package com.ccong.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by: ccong
 * Date: 18/9/22 下午10:52
 */
public class TicketDTO implements Serializable {

    private long id;
    private String name;
    private Long owner;
    private long lockerUser;
    private String ticketNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public long getLockerUser() {
        return lockerUser;
    }

    public void setLockerUser(long lockerUser) {
        this.lockerUser = lockerUser;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDTO ticket = (TicketDTO) o;

        if (id != ticket.id) return false;
        return name != null ? name.equals(ticket.name) : ticket.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", lockerUser=" + lockerUser +
                ", ticketNumber='" + ticketNumber + '\'' +
                '}';
    }
}
