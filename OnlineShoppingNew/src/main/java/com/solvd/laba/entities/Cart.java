package com.solvd.laba.entities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Cart implements CartInterface {
    private static final Logger logger = LogManager.getLogger(Cart.class);
    private double totalPrice = 0;
    private HashMap<Product,Integer> cartContentsHashMap = new HashMap<>();
    private final DecimalFormat df = new DecimalFormat("#.##");
    private ProductCategory category;
    private final static int CART_SIZE = 10;
    public Cart() {
        logger.log(Level.INFO, "Cart object created");
    }

    public void addProducts(Product product, int count) {
        if (cartContentsHashMap.size() >= CART_SIZE) {
            logger.log(Level.INFO,"Adding product to a full cart");
            System.out.println("Maximum cart size reached. Remove some items from the cart");
            return;
        }
        else if (product.isInStock()) {
            logger.log(Level.INFO, "Product added to cart");
            cartContentsHashMap.put(product,count);
            product.setProductStock(product.getProductStock()-count);
            category = product.getProductCategory();
        } else {
            System.out.println(product.getProductName()+" - Out of Stock!");
        }
        try (FileOutputStream fos = new FileOutputStream("ShoppingCart.txt")) {
            for (int i = 0; i < cartContentsHashMap.size(); i++) {
                String[] arr = new String[cartContentsHashMap.size()];
                for (Map.Entry<Product,Integer> keyValues : cartContentsHashMap.entrySet()){
                    arr[i] = keyValues.getKey() + "-" + keyValues.getValue();
                    fos.write(arr[i].getBytes());
                }
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void removeProducts(Product product) {
        int count;
        if(cartContentsHashMap.containsKey(product)) {
            count = cartContentsHashMap.get(product);
            logger.log(Level.INFO, "Product removed from cart");
            cartContentsHashMap.remove(product,count);
            product.setProductStock(product.getProductStock()+count);
        } else {
            System.out.println(product+" is not available in the cart");
            logger.log(Level.INFO,"Trying to remove product that is not available in cart");
        }
    }

    public double getTotalPrice() {
        logger.log(Level.INFO,"Get total price function");
        double currentTotal;
        double currentTotalWithTax;

        for (Map.Entry<Product,Integer> cartElements : cartContentsHashMap.entrySet()) {
            Product product = cartElements.getKey();
            int count = cartElements.getValue();
            TwoNumberCalculationFunction<Double,Integer,Double> calculateCurrentTotal = (price,count1) -> price*count1;
            currentTotal = calculateCurrentTotal.doCalculate(product.getProductPrice(),count);
            OneNumberCalculationFunction<Double> calculateSalesTax = total -> (total + (total * (product.getProductCategory().getSalesTax())));
            currentTotalWithTax = calculateSalesTax.calculate(currentTotal);
            totalPrice += currentTotalWithTax;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(totalPrice));
    }

    public void viewCart() {
        double currentTotal;
        double currentTotalWithTax;

        for (Map.Entry<Product,Integer> cartElements : cartContentsHashMap.entrySet()) {
            Product product = cartElements.getKey();
            int count = cartElements.getValue();
            TwoNumberCalculationFunction<Double,Integer,Double> calculateCurrentTotal = (price,count1) -> price*count1;
            currentTotal = calculateCurrentTotal.doCalculate(product.getProductPrice(),count);
            OneNumberCalculationFunction<Double> calculateSalesTax = total -> (total + (total * (product.getProductCategory().getSalesTax())));
            currentTotalWithTax = calculateSalesTax.calculate(currentTotal);
            System.out.println(product + " ,Product Count: " + count + " ,Total:" + df.format(currentTotalWithTax)+" (Sales Tax:"+product.getProductCategory().getSalesTax()+" included)");
        }
        System.out.println("---------------------------------------------------");
        System.out.println("Total Price: " + df.format(this.getTotalPrice() ));
        logger.log(Level.INFO, "Viewing Cart");
        this.setTotalPrice(0);
    }

    @Override
    public String toString() {
        return "Products: " + cartContentsHashMap + " " + "Total Price: " + this.getTotalPrice();
    }

    public void setTotalPrice(double totalPrice) {
        logger.log(Level.INFO,"Set Total price method");
        this.totalPrice = totalPrice;
    }
    
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public HashMap<Product, Integer> getCartContentsHashMap() {
        return cartContentsHashMap;
    }

    public void setCartContentsHashMap(HashMap<Product, Integer> cartContentsHashMap) {
        this.cartContentsHashMap = cartContentsHashMap;
    }
}
