package com.solvd.laba.exceptions;

public class NotValidCardNoException extends Exception {
    private final String cardNo;
    private final String errorMsg;

    public NotValidCardNoException(String errorMsg, String cardNo) {
        this.errorMsg = errorMsg;
        this.cardNo = cardNo;
    }

    @Override
    public String getMessage() {
        return errorMsg+" Card No:"+ cardNo.substring(cardNo.length()-4);
    }
}
