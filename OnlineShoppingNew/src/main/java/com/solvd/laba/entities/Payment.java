package com.solvd.laba.entities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;
import java.util.function.Supplier;

public final class Payment {
    private static final Logger logger = LogManager.getLogger(Payment.class);
    private String paymentId;
    private String cardType;
    private String cardNo;
    private int cardNoInteger;
    private OrderStatus status;
    private PaymentMethod payMethod;
    
    public Payment( String cardType, String cardNo, PaymentMethod payMethod) throws NotValidCardNoException, NumberFormatException {
        this.paymentId = this.generatePaymentId();
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.payMethod = payMethod;
        if (cardNo.length() != 16) {
            throw new NotValidCardNoException("Card number length error", cardNo);
        }
        try {
            cardNoInteger = Integer.parseInt(cardNo);
        } catch(NumberFormatException nfe) {
             logger.log(Level.INFO,nfe.getMessage());
        }
    }

    public void makePurchase(double totalPrice) {
        System.out.println("Payment method selected: "+this.payMethod);
        System.out.println("Paid "+totalPrice+"$ using "+this.cardType+" card ending with "+this.cardNo.substring(12));
        System.out.println("Purchase Successful! "+"Payment Confirmation No: "+this.paymentId);
        this.status = OrderStatus.PAYMENT_DONE;
    }

    public String generatePaymentId() {
        Supplier<String> paymentID = () -> UUID.randomUUID().toString();
        return paymentID.get();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getCardNoInteger() {
        return cardNoInteger;
    }

    public void setCardNoInteger(int cardNoInteger) {
        this.cardNoInteger = cardNoInteger;
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

    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }
}
