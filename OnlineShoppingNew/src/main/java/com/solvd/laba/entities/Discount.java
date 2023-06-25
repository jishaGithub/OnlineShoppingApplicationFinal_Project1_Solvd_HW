package com.solvd.laba.entities;

public final class Discount implements DiscountInterface {

    private Boolean newUser = true;
    private int discountId;

    public Discount(int discountId, Boolean newUser) {
        this.discountId = discountId;
        this.newUser = newUser;
    }
    
    public static boolean isEligibleForFreeShipping(double total){
        ComparePredicate<Double> freeShipping = totalPrice -> totalPrice >50;
        return freeShipping.compare(total);
    }
    
    public double discountTotalPrice(double totalPrice, Boolean newUser) {
        double discountedTotal;
        if (newUser) {
            discountedTotal = DiscountType.NEW_USER.calculateDiscount(totalPrice);
            return this.newUser(discountedTotal);
        } else if (isEligibleForFreeShipping(totalPrice)) {
            discountedTotal = DiscountType.SHIPPING_DISCOUNT.calculateDiscount(totalPrice);
            return this.freeShipping(discountedTotal);
        } else {
            System.out.println("No eligible discount applied");
        }
        return totalPrice;
    }

    @Override
    public double newUser(double totalPrice) {
        System.out.println("New User discount applied.");
        return totalPrice;
    }

    @Override
    public double freeShipping(double totalPrice) {
        System.out.println("Free Shipping discount applied.");
        return totalPrice;
    }    

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
