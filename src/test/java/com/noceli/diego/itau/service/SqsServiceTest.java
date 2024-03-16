package com.noceli.diego.itau.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class SqsServiceTest {

    @Mock
    private AmazonSQS sqsClient;

    private SqsService sqsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sqsService = new SqsService(sqsClient);
    }

    @Test
    void sendMessageToQueue() {
        String message = "Test message";
        String queueUrl = "http://localhost:4566";

        sqsService.sendMessageToQueue(message);

        SendMessageRequest expectedRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message);
        verify(sqsClient).sendMessage(expectedRequest);
    }
}
