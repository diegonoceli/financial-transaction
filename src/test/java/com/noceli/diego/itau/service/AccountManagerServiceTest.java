package com.noceli.diego.itau.service;

import com.noceli.diego.itau.client.AccountFeignClient;
import com.noceli.diego.itau.client.model.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AccountManagerServiceTest {

    @Mock
    private AccountFeignClient accountFeignClient;

    @InjectMocks
    private AccountManagerService accountManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountDetailsSuccess() {
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(1000), BigDecimal.valueOf(1000));
        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetAccountDetailsInactiveAccount() {
        AccountResponse expectedResponse = new AccountResponse("123", "inactive", BigDecimal.valueOf(0), BigDecimal.valueOf(0));

        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        assertEquals(false, actualResponse.isActive());
    }

    @Test
    void testGetAccountDetailsInsufficientFunds() {
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);


        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        assertEquals(false, actualResponse.hasSufficientFunds(BigDecimal.valueOf(1000)));
    }

    @Test
    void testGetAccountDetailsExceededDailyLimit() {
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(1500), BigDecimal.valueOf(1000));

        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        assertFalse(actualResponse.hasDailyLimit(BigDecimal.valueOf(2000)));
    }
}
