package com.codecool.shop.model;

public class Payment extends  BaseModel{


    private String name;

    public Payment(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
