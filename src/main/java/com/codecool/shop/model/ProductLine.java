package com.codecool.shop.model;

public class ProductLine {

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
}
