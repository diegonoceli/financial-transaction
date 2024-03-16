package com.noceli.diego.itau.service;

import com.noceli.diego.itau.client.AccountFeignClient;
import com.noceli.diego.itau.client.model.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    public AccountResponse getAccountDetails(String accountId) {
        return accountFeignClient.getAccountDetails(accountId);
    }
}