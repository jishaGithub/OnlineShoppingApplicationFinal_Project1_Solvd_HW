package com.solvd.laba.entities;

public interface CartInterface {
    void addProducts(Product product,int count);
    void removeProducts(Product product);
    void viewCart();
}
