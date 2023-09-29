package com.solvd.laba.exceptions;

public class SameValueException extends Exception {
    private final String sameValue;

    public SameValueException(String sameValue) {
        this.sameValue  = sameValue;
    }

    @Override
    public String getMessage() {
        return "Error!! UserName and password cannot be same: "+sameValue;
    }
}
