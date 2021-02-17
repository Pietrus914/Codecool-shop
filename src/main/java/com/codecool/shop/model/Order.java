package com.codecool.shop.model;

import com.google.gson.annotations.Expose;

public class Order implements Gsonable {

    private int id;

    private Cart cart;

    private Payment payment;
    private Customer customer;

    public Order(Cart cart){
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
