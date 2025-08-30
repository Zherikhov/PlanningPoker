package com.zherikhov.planningpoker.infrastructure.messaging;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StompPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public StompPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publish(String destination, Object payload) {
        messagingTemplate.convertAndSend(destination, payload);
    }
}
