package com.solvd.laba.entities;

public interface DiscountInterface {
    double newUser(double totalPrice);
    double freeShipping(double totalPrice);
}