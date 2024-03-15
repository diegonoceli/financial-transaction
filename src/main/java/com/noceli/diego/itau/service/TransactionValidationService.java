package com.noceli.diego.itau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionValidationService {

    @Autowired
    private AccountManagerService accountManagerService;
    public ResponseEntity<String> executeTransaction(){
        return accountManagerService.getAccountDetails("123");

    }


}
