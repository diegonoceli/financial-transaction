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
        // Mocked account response
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(1000), BigDecimal.valueOf(1000));
        // Stubbing the Feign client to return the expected response
        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        // Calling the method under test
        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        // Asserting the actual response matches the expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetAccountDetailsInactiveAccount() {
        // Mocked account response for an inactive account
        AccountResponse expectedResponse = new AccountResponse("123", "inactive", BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        // Stubbing the Feign client to return the expected response
        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        // Calling the method under test
        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        // Asserting the account is inactive
        assertEquals(false, actualResponse.isActive());
    }

    @Test
    void testGetAccountDetailsInsufficientFunds() {
        // Mocked account response with insufficient funds
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        // Stubbing the Feign client to return the expected response
        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        // Calling the method under test
        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        // Asserting there are insufficient funds in the account
        assertEquals(false, actualResponse.hasSufficientFunds(BigDecimal.valueOf(1000)));
    }

    @Test
    void testGetAccountDetailsExceededDailyLimit() {
        // Mocked account response with exceeded daily limit
        AccountResponse expectedResponse = new AccountResponse("123", "active", BigDecimal.valueOf(1500), BigDecimal.valueOf(1000));
        // Stubbing the Feign client to return the expected response
        when(accountFeignClient.getAccountDetails(anyString())).thenReturn(expectedResponse);

        // Calling the method under test
        AccountResponse actualResponse = accountManagerService.getAccountDetails("123");

        // Asserting the transaction amount exceeds the daily limit
        assertEquals(false, actualResponse.hasDailyLimit(BigDecimal.valueOf(2000)));
    }
}
