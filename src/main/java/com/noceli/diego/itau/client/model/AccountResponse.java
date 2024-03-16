package com.noceli.diego.itau.client.model;

import java.math.BigDecimal;

public class AccountResponse {
    private String accountId;
    private String status;
    private BigDecimal balance;
    private BigDecimal dailyLimit;

    public AccountResponse() {
    }

    public AccountResponse(String accountId, String status, BigDecimal balance, BigDecimal dailyLimit) {
        this.accountId = accountId;
        this.status = status;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
    }

    public Boolean isActive() {
        return status.equals("active");
    }

    public Boolean hasSufficientFunds(BigDecimal transactionAmount) {
        return balance.compareTo(transactionAmount) >= 0;
    }

    public boolean hasDailyLimit(BigDecimal transactionAmount) {
        return dailyLimit.compareTo(transactionAmount) >= 0;
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
