package com.noceli.diego.itau.client.model;

import java.math.BigDecimal;

public class BacenTransactionRequest {
    private String accountId;
    private BigDecimal transactionAmount;

    public BacenTransactionRequest(String accountId, BigDecimal transactionAmount) {
        this.accountId = accountId;
        this.transactionAmount = transactionAmount;
    }

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
