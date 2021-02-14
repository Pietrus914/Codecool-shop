package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Payment extends  BaseModel{


    private String name;
    private List<Payment> payments;

    public Payment(String name) {
        super(name);
        this.name = name;
        this.payments = new ArrayList<>();
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
