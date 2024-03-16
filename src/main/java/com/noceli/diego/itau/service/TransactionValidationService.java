package com.noceli.diego.itau.service;

import com.noceli.diego.itau.client.model.AccountResponse;
import com.noceli.diego.itau.exception.DailyLimitExceededException;
import com.noceli.diego.itau.exception.InactiveCheckingAccountException;
import com.noceli.diego.itau.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidationService {

    @Autowired
    private AccountManagerService accountManagerService;
    @Autowired
    private TransactionService transactionService;

    public AccountResponse executeTransaction(BigDecimal transactionAmount, String accountId) {
        AccountResponse accountResponse = accountManagerService.getAccountDetails(accountId);

        if (!accountResponse.isActive()) {
            throw new InactiveCheckingAccountException();
        }
        if (!accountResponse.hasDailyLimit(transactionAmount)) {
            throw new DailyLimitExceededException();
        }
        if (!accountResponse.hasSufficientFunds(transactionAmount)) {
            throw new InsufficientFundsException();
        }

        transactionService.notifyBacen(accountId, transactionAmount);
        return accountResponse;
    }


}
