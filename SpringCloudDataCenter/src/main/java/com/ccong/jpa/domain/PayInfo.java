package com.ccong.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by: ccong
 * Date: 18/9/23 下午7:03
 */
@Entity(name="pay_info")
public class PayInfo {
    @Id
    @GeneratedValue
    private long id;

    private long orderId;

    private String status;

    private int amount;

    private long customerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", customerId=" + customerId +
                '}';
    }
}
