package com.solvd.laba.exceptions;

public class NotValidZipException extends Exception {
    private final int zipCode;

    public NotValidZipException(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getMessage() {
        return "Invalid zip code number format: " + zipCode;
    }
}
