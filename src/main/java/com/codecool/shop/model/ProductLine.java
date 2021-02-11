package com.codecool.shop.model;

public class ProductLine {
    private static int counter = 0;

    private Product product;
    private int quantity;


    public ProductLine(Product product){
        this.product = product;
        this.quantity = 1;
    }

    public ProductLine(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }



    public float getTotalPrice(){
        return quantity * product.getDefaultPrice();
    }

    public String getTotalPriceString(){
        return getTotalPrice() + " " + product.getDefaultCurrency();
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity(int i) {
        setQuantity(quantity + i);
    }
}
