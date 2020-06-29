package com.rest.jwebapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rest.jwebapp.dto.ObjectType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    public WsSender(SimpMessagingTemplate simpMessagingTemplate, ObjectMapper objectMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> Consumer<T> getWebSocketSenderMethod(ObjectType objectType, Class view) {
        return (T payload) -> {
            String value = null;
            try {
                value = objectMapper.writeValueAsString(payload);
            }
            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            simpMessagingTemplate.convertAndSend(
                    "/topic/activity",
                    value
            );
        };
    }

    private ObjectWriter getWriterForMethodSender(Class view) {
        ObjectWriter objectWriter = objectMapper.setConfig(objectMapper
                .getSerializationConfig())
                .writerWithView(view);
        return objectWriter;
    }
}
