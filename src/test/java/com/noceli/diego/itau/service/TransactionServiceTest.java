package com.noceli.diego.itau.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noceli.diego.itau.client.BacenFeignClient;
import com.noceli.diego.itau.client.model.BacenTransactionRequest;
import com.noceli.diego.itau.model.TransactionSqsMessage;
import com.noceli.diego.itau.service.SqsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private BacenFeignClient bacenFeignClient;

    @Mock
    private SqsService sqsService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotifyBacenSuccess() {
        BacenTransactionRequest request = new BacenTransactionRequest("123", BigDecimal.TEN);
        ResponseEntity<Void> successResponse = ResponseEntity.ok().build();

        when(bacenFeignClient.notifyTransaction(request)).thenReturn(successResponse);

        transactionService.notifyBacen("123", BigDecimal.TEN);
    }

    @Test
    void testNotifyBacenFailure() throws JsonProcessingException {
        BacenTransactionRequest request = new BacenTransactionRequest("123", BigDecimal.TEN);
        ResponseEntity<Void> errorResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        when(bacenFeignClient.notifyTransaction(request)).thenReturn(errorResponse);

        doNothing().when(sqsService).sendMessageToQueue(any());

        transactionService.notifyBacen("123", BigDecimal.TEN);
    }

    @Test
    void testNotifyBacenRetry() {
        BacenTransactionRequest request = new BacenTransactionRequest("123", BigDecimal.TEN);
        ResponseEntity<Void> errorResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();

        when(bacenFeignClient.notifyTransaction(request)).thenReturn(errorResponse);

        doNothing().when(sqsService).sendMessageToQueue(any());

        transactionService.notifyBacen("123", BigDecimal.TEN);
    }

    @Test
    void testFallbackMethodForServiceCall() throws JsonProcessingException {
        String accountId = "123";
        BigDecimal transactionAmount = BigDecimal.TEN;
        TransactionSqsMessage message = new TransactionSqsMessage(accountId, transactionAmount);
        String messageJson = "your_message_json_representation";

        when(objectMapper.writeValueAsString(message)).thenReturn(messageJson);

        doNothing().when(sqsService).sendMessageToQueue(messageJson);

        transactionService.fallbackMethodForServiceCall(accountId, transactionAmount);
    }

    @Test
    void testFallbackMethodForServiceCallWithJsonProcessingException() throws JsonProcessingException {
        String accountId = "123";
        BigDecimal transactionAmount = BigDecimal.TEN;
        TransactionSqsMessage message = new TransactionSqsMessage(accountId, transactionAmount);

        when(objectMapper.writeValueAsString(message)).thenThrow(JsonProcessingException.class);

        doThrow(RuntimeException.class).when(sqsService).sendMessageToQueue(any());
    }
}
