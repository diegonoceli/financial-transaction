package com.noceli.diego.itau.service;

import com.noceli.diego.itau.client.model.AccountResponse;
import com.noceli.diego.itau.exception.DailyLimitExceededException;
import com.noceli.diego.itau.exception.InactiveCheckingAccountException;
import com.noceli.diego.itau.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionValidationServiceTest {

    @Mock
    private AccountManagerService accountManagerService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionValidationService transactionValidationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteTransactionWithActiveAccountAndAvailableLimit() {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setStatus("active");
        accountResponse.setDailyLimit(BigDecimal.valueOf(1000));
        accountResponse.setBalance(BigDecimal.valueOf(5000));

        when(accountManagerService.getAccountDetails(anyString())).thenReturn(accountResponse);

        transactionValidationService.executeTransaction(BigDecimal.valueOf(500), "123");

        verify(transactionService, times(1)).notifyBacen(anyString(), any(BigDecimal.class));
    }

    @Test
    void testExecuteTransactionWithInactiveAccount() {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setStatus("banned");

        when(accountManagerService.getAccountDetails(anyString())).thenReturn(accountResponse);

        assertThrows(InactiveCheckingAccountException.class, () ->
                transactionValidationService.executeTransaction(BigDecimal.valueOf(500), "123"));
    }

    @Test
    void testExecuteTransactionWithUnavailableLimit() {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId("123");
        accountResponse.setStatus("active");
        accountResponse.setDailyLimit(BigDecimal.valueOf(1100));
        accountResponse.setBalance(BigDecimal.valueOf(90));

        when(accountManagerService.getAccountDetails(anyString())).thenReturn(accountResponse);

        assertThrows(InsufficientFundsException.class, () ->
                transactionValidationService.executeTransaction(BigDecimal.valueOf(1000), "123"));
    }

    @Test
    void testExecuteTransactionWithExceededDailyLimit() {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setStatus("active");
        accountResponse.setDailyLimit(BigDecimal.valueOf(1000));
        accountResponse.setBalance(BigDecimal.valueOf(5000));

        when(accountManagerService.getAccountDetails(anyString())).thenReturn(accountResponse);

        assertThrows(DailyLimitExceededException.class, () ->
                transactionValidationService.executeTransaction(BigDecimal.valueOf(1500), "123"));
    }
}
