package com.noceli.diego.itau.exception;

public class InactiveCheckingAccountException extends RuntimeException {
    public InactiveCheckingAccountException() {
        super("Inactive checking account.");
    }
}