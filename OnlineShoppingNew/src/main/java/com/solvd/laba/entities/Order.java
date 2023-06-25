package com.solvd.laba.entities;

import java.time.LocalDate;

public class Order {
    private static int counter = 1;
    private static double federalTax = .5;
    private LocalDate currentDate = LocalDate.now();
    private int orderId;    
    private double orderTotal;
    private OrderStatus status;    
    private Customer customer;
    
    public Order(Customer customer, Cart cart) {
        setOrderId(counter++);
        this.orderTotal = cart.getTotalPrice();
        this.customer = customer;
        this.status = OrderStatus.ORDER_PLACED;
    }

    @Override
    public String toString() {
        return this.orderId+" " + " | Order placed on: "+ this.getCurrentDate() + " by "+customer.name+"  | Total: " + this.orderTotal + "\n";
    }
    
    public static void setFederalTax(double federalTax) {
        Order.federalTax = federalTax;
    }
    
    public static double getFederalTax() {
        return federalTax;
    }   
    
    public LocalDate getCurrentDate() {
        return this.currentDate;
    }
    
    public double getOrderTotal() {
        return orderTotal;
    }

    public final double addTaxes(Double total) {
        double tax = total * federalTax;
        TwoNumberCalculationFunction<Double,Double,Double> getTotalWithTaxes = (total1,tax1) -> total1 + tax1;
        return getTotalWithTaxes.doCalculate(total,tax);
    }

    public OrderStatus getStatus() {
        return OrderStatus.ORDER_PLACED;
    }

    public void setStatus(OrderStatus status1) {
        status = status;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }     

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
