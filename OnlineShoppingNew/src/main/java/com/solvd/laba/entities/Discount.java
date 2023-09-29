package com.solvd.laba.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Discount implements DiscountInterface {
    private static final Logger logger = LogManager.getLogger(Discount.class);
    private Boolean newUser;
    private final double totalPrice;

    public Discount(double totalPrice, Boolean newUser) {
        this.totalPrice = totalPrice;
        this.newUser = newUser;
    }

    public double discountTotalPrice() {
        double discountedTotal;

        if (newUser) {
            discountedTotal = DiscountType.NEW_USER.calculateDiscount(totalPrice);
            return this.newUser(discountedTotal);
        }

        if (isEligibleForFreeShipping(totalPrice)) {
            discountedTotal = DiscountType.SHIPPING_DISCOUNT.calculateDiscount(totalPrice);
            return this.freeShipping(discountedTotal);
        } else {
            logger.info("No eligible discount applied");
            return totalPrice;
        }
    }

    @Override
    public double newUser(double totalPrice) {
        logger.info("New User discount applied.");
        return totalPrice;
    }

    @Override
    public double freeShipping(double totalPrice) {
        logger.info("Free Shipping discount applied.");
        return totalPrice;
    }

    public static boolean isEligibleForFreeShipping(double total) {
        ComparePredicate<Double> freeShipping = totalPrice -> totalPrice > 50;
        return freeShipping.compare(total);
    }

}
