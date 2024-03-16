package com.noceli.diego.itau.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.noceli.diego.itau.client.BacenFeignClient;
import com.noceli.diego.itau.client.model.BacenTransactionRequest;
import com.noceli.diego.itau.model.TransactionSqsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@EnableHystrix
@EnableRetry
public class TransactionService {

    @Autowired
    BacenFeignClient bacenFeignClient;

    @Autowired
    SqsService sqsService;

    @Autowired
    ObjectMapper objectMapper;

    @HystrixCommand(fallbackMethod = "fallbackMethodForServiceCall")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 100))
    public void notifyBacen(String accountId, BigDecimal transactionAmount) {
        bacenFeignClient.notifyTransaction(new BacenTransactionRequest(accountId, transactionAmount));
    }

    public void fallbackMethodForServiceCall(String accountId, BigDecimal transactionAmount) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(new TransactionSqsMessage(accountId, transactionAmount));

        sqsService.sendMessageToQueue(message);
    }
}
