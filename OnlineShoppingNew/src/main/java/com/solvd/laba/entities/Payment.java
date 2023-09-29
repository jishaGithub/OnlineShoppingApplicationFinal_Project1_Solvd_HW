package com.solvd.laba.entities;

import java.util.UUID;
import java.util.function.Supplier;

public final class Payment {
    private final String paymentId;
    private final String cardType;
    private OrderStatus status;
    private PaymentMethod payMethod;

    public Payment(String cardType, PaymentMethod payMethod) {
        this.paymentId = generatePaymentId();
        this.cardType = cardType;
        this.payMethod = payMethod;
    }

    public String generatePaymentId() {
        Supplier<String> paymentID = () -> UUID.randomUUID().toString();
        return paymentID.get();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCardType() {
        return cardType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPayMethod() {
        return payMethod;
    }

}
