package com.solvd.laba.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.function.UnaryOperator;

public class Shipping implements ShippingInterface {
    private static final double shippingCost;

    static {
        shippingCost = 0;
    }
    private static final Logger logger = LogManager.getLogger(Shipping.class);
    private static final int shippingId = 0;
    private String shippingCompany;
    private OrderStatus status;

    private ShippingPreference shipPreference;

    public Shipping(String shippingCompany, ShippingPreference shipPreference) {
        this.shipPreference = shipPreference;
        this.shippingCompany = shippingCompany;
    }

    public final double calculateShippingCost(ShippingPreference shippingPref, double totalPrice) {
        this.shipPreference = shippingPref;
        if (shippingPref.equals(ShippingPreference.Domestic)) {
            return this.domestic(totalPrice);
        } else if (shippingPref.equals(ShippingPreference.International)) {
            return this.international(totalPrice);
        } else {
            return shippingCost;
        }
    }

    public void displayShippingConfirmation(Order order) {
        logger.info("Order ID:"+order.getOrderId()
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

    public ShippingPreference getShipPreference() {
        return shipPreference;
    }

    public void setShipPreference(ShippingPreference shipPreference) {
        this.shipPreference = shipPreference;
    }
}
