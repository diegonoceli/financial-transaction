package com.noceli.diego.itau.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Unavailable limit in the checking account.");
    }
}