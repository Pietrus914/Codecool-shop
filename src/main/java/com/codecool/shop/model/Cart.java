package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ProductLine> productLines;

    public Cart(){
        this.productLines = new ArrayList<>();
    }


    public void add(ProductLine productLine){
        productLines.add(productLine);
    }


    public float getTotalPrice(){

        float totalPrice = productLines.stream()
                .map(prod -> prod.getTotalPrice())
                .reduce(0.0f, (a,b) -> a + b);

        return totalPrice;
    }



    public List<ProductLine> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<ProductLine> productLines) {
        this.productLines = productLines;
    }
}
