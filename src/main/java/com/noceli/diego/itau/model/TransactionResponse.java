package com.noceli.diego.itau.model;

import java.math.BigDecimal;

public class TransactionResponse {
    private String accountId;
    private String status;
    private BigDecimal balance;
    private BigDecimal dailyLimit;

    public TransactionResponse(String accountId, String status, BigDecimal balance, BigDecimal dailyLimit) {
        this.accountId = accountId;
        this.status = status;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
}
