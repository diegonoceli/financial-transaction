package com.noceli.diego.itau.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Component;

@Component
public class SqsService {

    private final AmazonSQS sqsClient;

    public SqsService() {
        this.sqsClient = AmazonSQSClientBuilder.standard().build();
    }

    public void sendMessageToQueue(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl("http://localhost:4566")
                .withMessageBody(message);
        sqsClient.sendMessage(sendMessageRequest);
    }
}
