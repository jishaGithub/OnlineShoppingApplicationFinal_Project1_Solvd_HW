package com.solvd.laba.exceptions;

public class NotPositiveException extends Exception {
    private final int count;
    private final double price;

    public NotPositiveException(int count, double price) {
        this.count = count;
        this.price = price;
    }

    @Override
    public String getMessage() {
        return "These values should be positive: Product stock: " + count + " Price of product: " + price;
    }
}
