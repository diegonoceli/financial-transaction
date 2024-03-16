package com.noceli.diego.itau.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SqsServiceTest {

    @Mock
    private AmazonSQS sqsClient;

    @InjectMocks
    private SqsService sqsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessageToQueueSuccess() {
        String message = "Test message";

        sqsService.sendMessageToQueue(message);

        verify(sqsClient, times(1)).sendMessage(any(SendMessageRequest.class));
    }

    @Test
    void testSendMessageToQueueWithNullMessage() {
        String message = null;

        sqsService.sendMessageToQueue(message);

        verifyNoInteractions(sqsClient);
    }
}
