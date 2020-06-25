package com.rest.jwebapp.controller;

import com.rest.jwebapp.domain.Message;
import com.rest.jwebapp.repo.MessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Log
@RestController
@RequestMapping("collection")
public class MainRestController {

    private final MessageRepository messageRepository;

    @Autowired
    public MainRestController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
        return messageRepository.save(messageFromFrontEnd);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message messageFromFrontEnd) {
        BeanUtils.copyProperties(messageFromFrontEnd, messageFromDb, "id");
        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message messageFromFrontEnd) {
        messageRepository.delete(messageFromFrontEnd);
    }
}
