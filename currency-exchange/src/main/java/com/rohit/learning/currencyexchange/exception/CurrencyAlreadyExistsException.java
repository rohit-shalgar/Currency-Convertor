package com.rohit.learning.currencyexchange.exception;

public class CurrencyAlreadyExistsException extends RuntimeException {

    public CurrencyAlreadyExistsException(String message) {
        super(message);
    }
}
