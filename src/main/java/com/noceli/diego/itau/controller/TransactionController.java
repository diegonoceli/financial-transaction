package com.noceli.diego.itau.controller;

import com.noceli.diego.itau.client.model.AccountResponse;
import com.noceli.diego.itau.model.TransactionRequest;
import com.noceli.diego.itau.model.TransactionResponse;
import com.noceli.diego.itau.service.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransactionController {

    @Autowired
    private TransactionValidationService transactionValidationService;

    @PostMapping("/account")
    public TransactionResponse getAccount(@RequestBody TransactionRequest transactionRequest) throws Exception {
        AccountResponse response = transactionValidationService.executeTransaction(transactionRequest.getTransactionAmount(), transactionRequest.getAccountId());
        return createResponse(response, transactionRequest.getTransactionAmount());
    }


    private TransactionResponse createResponse(AccountResponse response, BigDecimal transactionAmount) {
        return new TransactionResponse(response.getAccountId(),
                response.getStatus(),
                response.getBalance().subtract(transactionAmount),
                response.getDailyLimit().subtract(transactionAmount));
    }
}