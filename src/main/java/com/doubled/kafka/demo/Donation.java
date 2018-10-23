package com.doubled.kafka.demo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Donation implements Serializable {
    private String name;
    private BigDecimal amount;
    private String consumer;

    public Donation() {
    }

    public Donation(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
