package com.solvd.laba.entities;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card");

    private final String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
