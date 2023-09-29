package com.solvd.laba.entities;

public enum DiscountType {
    NEW_USER {
        @Override
        public double calculateDiscount(double total) {
            return total*.85;
        }
    },SHIPPING_DISCOUNT {
        @Override
        public double calculateDiscount(double total) {
            return total*.90;
        }
    };

    public abstract double calculateDiscount(double total);
}
