package com.solvd.laba.entities;

public enum ProductCategory {
    FRUITS("Fruits",0.05) {
        @Override
        public double getSalesTax() {
            return ProductCategory.FRUITS.salesTax;
        }
    },CANDY("Candy",0.02) {
        @Override
        public double getSalesTax() {
            return ProductCategory.CANDY.salesTax;
        }
    },CHEESE("CHEESE",0.03) {
        @Override
        public double getSalesTax() {
            return ProductCategory.CHEESE.salesTax;
        }
    };

    private final String name;
    private final double salesTax;

    ProductCategory(String name, double salesTax){
        this.name = name;
        this.salesTax = salesTax;
    }

    public String getName() {
        return name;
    }

    public abstract double getSalesTax();
}
