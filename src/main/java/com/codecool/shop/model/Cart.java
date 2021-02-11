package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {



    private String currency;
    private Map<String, ProductLine> productLines;

    public Cart(){
        this.productLines = new HashMap<String, ProductLine>();
        currency = "USD";
    }

    public void add(ProductLine productLine){
        productLines.put(productLine.getProduct().getName(), productLine);
    }


    public float getTotalPrice(){

        float totalPrice = productLines.values().stream()
                .map(v -> v.getTotalPrice())
                .reduce(0.0f, (a,b) -> a + b);

        return totalPrice;
    }

    public String getTotalPriceString(){
        return ""+ getTotalPrice() + " " + currency ;
    }



    public Map<String, ProductLine> getProductLines() {
        return productLines;
    }

    public void setProductLines(Map<String, ProductLine> productLines) {
        this.productLines = productLines;
    }


    public void remove(String name){
            productLines.remove(name);
    }

    public void decreaseQuantity(String name){
        ProductLine line = productLines.get(name);
        if (line.getQuantity() > 1){
            productLines.get(name).changeQuantity(-1);
        } else if (line.getQuantity() == 1){
            remove(name);
        }
    }

    public void increaseQuantity(String name){
        ProductLine line = productLines.get(name);
        line.changeQuantity(1);
    }

    public int getSize(){
        return productLines.size();
    }


}
