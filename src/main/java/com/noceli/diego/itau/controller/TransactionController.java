package com.noceli.diego.itau.controller;

import com.noceli.diego.itau.service.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionValidationService transactionValidationService;

    @GetMapping("/account")
    public ResponseEntity<String> getAccount() {
        return transactionValidationService.executeTransaction();
    }
}