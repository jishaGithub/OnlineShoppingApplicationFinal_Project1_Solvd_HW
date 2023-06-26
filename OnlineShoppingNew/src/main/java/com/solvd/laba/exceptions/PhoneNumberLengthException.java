package com.solvd.laba.exceptions;

public class PhoneNumberLengthException extends Exception {    
    private final String phoneNumber;
    private final String message;

    public PhoneNumberLengthException(String message, String phoneNumber) {
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getMessage() {
        return message+" : "+phoneNumber;
    }
}
