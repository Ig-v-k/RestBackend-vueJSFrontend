package com.rest.jwebapp.config;

import com.rest.jwebapp.domain.Message;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConvertStringToMessage implements Converter<String, Message> {

    @Override
    public Message convert(String s) {
        return new Message(s);
    }
}
