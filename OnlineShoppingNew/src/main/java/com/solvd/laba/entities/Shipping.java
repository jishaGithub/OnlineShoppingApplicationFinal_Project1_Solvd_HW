package com.solvd.laba.entities;

import java.util.function.UnaryOperator;

public class Shipping implements ShippingInterface {
    static {
        shippingCost = 0;
    }
    private static double shippingCost;
    private int shippingId;
    private String shippingCompany;
    private OrderStatus status;
    private ShippingPreference shipPreference;
    
    public Shipping(int shippingId, String shippingCompany) {
        this.shippingId = shippingId;
        this.shippingCompany = shippingCompany;
    }

    public final double calculateShippingCost(ShippingPreference shippingPref1, double totalPrice) {
        this.shipPreference = shippingPref1;
        if (shippingPref1.equals(ShippingPreference.Domestic)) {
            return this.domestic(totalPrice);
        } else if (shippingPref1.equals(ShippingPreference.International)) {
            return this.international(totalPrice);
        } else {
            return 0.0;
        }
    }

    public void displayShippingConfirmation() {
        System.out.println("The shipment ID: " + this.getShippingId()
                + " is shipping via " + this.getShippingCompany());
        this.status = OrderStatus.SHIPPED;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public int getShippingId() {
        return shippingId;
    }

    @Override
    public double domestic(double totalPrice) {
        return totalCost(totalPrice);
    }

    @Override
    public double international(double totalPrice) {
        return totalCost(totalPrice);
    }

    public double totalCost(double totalPrice) {
        UnaryOperator<Double> cost = totalPrice1 -> totalPrice1 + this.shipPreference.getCost();
        return cost.apply(totalPrice);
    }
}
