package com.solvd.laba.entities;

import java.util.function.Predicate;

public class Product {
    private int productId;
    private String productName;
    private ProductCategory productCategory;
    private int productStock;
    private double productPrice;

    public Product(int productId, String productName, ProductCategory productCategory, double productPrice, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }

    public Product(int productId, String productName, double productPrice, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return (product.productId == this.productId && product.productName.equals(this.productName)
                && product.productCategory == this.productCategory && product.productPrice == this.productPrice
                && product.productStock == this.productStock);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.productId;
        return result;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Product Name: " + productName + ", Product Category: " + productCategory + ", Product Price: " + productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getProductStock() {
        return productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public boolean isInStock() {
        Predicate<Product> isInStock = product -> product.getProductStock() > 0;
        return isInStock.test(this);
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
