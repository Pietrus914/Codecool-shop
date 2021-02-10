package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ProductLine> products;

    public Cart(){
        this.products = new ArrayList<>();
    }


    public float getTotalPrice(){

        float totalPrice = products.stream()
                .map(prod -> prod.getTotalPrice())
                .reduce(0.0f, (a,b) -> a + b);

        return totalPrice;
    }

    public List<ProductLine> getProducts() {
        return products;
    }

    public void setProducts(List<ProductLine> products) {
        this.products = products;
    }
}
