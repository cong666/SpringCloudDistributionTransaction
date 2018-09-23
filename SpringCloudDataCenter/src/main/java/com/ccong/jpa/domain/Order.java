package com.ccong.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;


@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid;

    private long customerId;

    private String title;

    private String ticketNumber;

    private int amount;

    private String status;

    private String errorReason;

    private ZonedDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", customerId=" + customerId +
                ", title='" + title + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", errorReason='" + errorReason + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
