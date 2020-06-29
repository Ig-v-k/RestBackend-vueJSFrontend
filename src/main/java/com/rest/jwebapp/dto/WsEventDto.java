package com.rest.jwebapp.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WsEventDto {
    private final ObjectType objectType;
    private final EventType eventType;

    @JsonRawValue
    private String body;
}
