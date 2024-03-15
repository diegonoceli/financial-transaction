package com.noceli.diego.itau.service;

import com.noceli.diego.itau.client.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    public ResponseEntity<String> getAccountDetails(String accountId) {
        return accountFeignClient.getAccountDetails("123");

    }
}