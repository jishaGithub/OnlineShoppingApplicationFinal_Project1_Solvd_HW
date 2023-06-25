package com.solvd.laba.entities;

public class PhoneNumberLengthException extends Exception {
    private final String phoneNumber;
    
    public PhoneNumberLengthException(String phoneNumber, String str) {
        super(str);
        this.phoneNumber = phoneNumber;
    }
}
