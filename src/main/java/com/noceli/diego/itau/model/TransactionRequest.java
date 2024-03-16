package com.noceli.diego.itau.model;

import java.math.BigDecimal;

public class TransactionRequest {
    private String accountId;
    private BigDecimal transactionAmount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
