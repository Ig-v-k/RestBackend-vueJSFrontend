package com.rest.jwebapp.controller;

import com.rest.jwebapp.domain.Message;
import com.rest.jwebapp.domain.Views;
import com.rest.jwebapp.dto.EventType;
import com.rest.jwebapp.dto.ObjectType;
import com.rest.jwebapp.repo.MessageRepository;
import com.rest.jwebapp.util.WsSender;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@Log
@RestController
@RequestMapping("collections")
public class MainRestController {

    private final MessageRepository messageRepository;
    private final BiConsumer<EventType, Message> wsSender; // response: {id}, {text}

    @Autowired
    public MainRestController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getWebSocketSenderMethod(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    public Message get(
            @PathVariable("id") Message messageFromDb) {
        return messageFromDb;
    }

    @PostMapping
    public Message create(
            @RequestBody Message messageFromFrontEnd) {
        Message saveMessage = messageRepository.save(messageFromFrontEnd);
        wsSender.accept(EventType.CREATE, saveMessage);
        return saveMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message messageFromFrontEnd) {
        BeanUtils.copyProperties(messageFromFrontEnd, messageFromDb, "id");
        Message updateMessage = messageRepository.save(messageFromFrontEnd);
        wsSender.accept(EventType.UPDATE, updateMessage);
        return updateMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message messageFromFrontEnd) {
        messageRepository.delete(messageFromFrontEnd);
        wsSender.accept(EventType.DELETE, messageFromFrontEnd);
    }
}
