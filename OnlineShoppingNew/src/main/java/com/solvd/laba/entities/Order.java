package com.solvd.laba.entities;

import java.time.LocalDate;

public class Order {
    private static int counter = 1;
    private static double federalTax = 0.5;
    private final LocalDate currentDate = LocalDate.now();
    private int orderId;
    private double orderTotal;
    private final Customer customer;
    private OrderStatus status;

    public Order(Customer customer, Cart cart) {
        setOrderId(counter++);
        this.orderTotal = cart.getTotalPrice();
        this.customer = customer;
    }

    @Override
    public String toString() {
        return this.orderId + " | Order placed on: " + this.getCurrentDate() + " by " + customer.name + " | Total: " + this.orderTotal + "\n";
    }

    public LocalDate getCurrentDate() {
        return this.currentDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public final double addTaxes(Double total) {
        double tax = (total * federalTax) / 100;
        TwoNumberCalculationFunction<Double, Double, Double> getTotalWithTaxes = (total1, tax1) -> total1 + tax1;
        return getTotalWithTaxes.doCalculate(total, tax);
    }

    public OrderStatus getStatus() {
        return OrderStatus.ORDER_PLACED;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public static double getFederalTax() {
        return federalTax;
    }

    public static void setFederalTax(double federalTax) {
        Order.federalTax = federalTax;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStatus(OrderStatus newStatus) {
        status = newStatus;
    }

}
