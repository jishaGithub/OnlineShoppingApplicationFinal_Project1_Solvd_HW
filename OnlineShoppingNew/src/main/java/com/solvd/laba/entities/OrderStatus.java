package com.solvd.laba.entities;

public enum OrderStatus {
    SHIPPED("Shipped"), ORDER_PLACED("Order Placed"), PAYMENT_DONE("Payment Done");
    private final String name;
    OrderStatus(String name){
        this.name = name;
    }

    public String getStatus(){
        return this.name;
    }
}