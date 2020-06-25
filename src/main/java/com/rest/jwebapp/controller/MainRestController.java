package com.rest.jwebapp.controller;

import com.rest.jwebapp.model.Message;
import com.rest.jwebapp.repo.MessageRepo;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log
@RestController
@RequestMapping("collection")
public class MainRestController {

    private final MessageRepo messageRepo;

    @Autowired
    public MainRestController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message get(
            @PathVariable("id") Message messageFromDb) {
        return messageFromDb;
    }

    @PostMapping
    public Message create(
            @RequestBody Message messageFromFrontEnd) {
        log.info("method - create - messageFromFrontEnd --> " + messageFromFrontEnd);
        return messageRepo.save(messageFromFrontEnd);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message messageFromFrontEnd) {
        log.info("method - update - messageFromFrontEnd --> " + messageFromFrontEnd);
        BeanUtils.copyProperties(messageFromFrontEnd, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message messageFromDb) {
        messageRepo.delete(messageFromDb);
    }
}
