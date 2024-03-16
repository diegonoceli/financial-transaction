package com.noceli.diego.itau.exception;

public class DailyLimitExceededException extends RuntimeException {
    public DailyLimitExceededException() {
        super("The transfer exceeded the daily limit of $1,000.00.");
    }
}