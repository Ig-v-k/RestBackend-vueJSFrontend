package com.rest.jwebapp.controller;

import com.rest.jwebapp.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("collection")
public class MainRestController {
    private int counter = 4;

    public List<Map<String, String>> collection_data = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {
                {
                    put("id", "1");
                }
                {
                    put("text", "First message");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("id", "2");
                }
                {
                    put("text", "Second message");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("id", "3");
                }
                {
                    put("text", "Third message");
                }
            });
        }
    };

    @GetMapping
    public List<Map<String, String>> list() {
        return this.collection_data;
    }

    @GetMapping("{id}")
    public Map<String, String> get(
            @PathVariable String id
    ) {
        return this.getMessage(id);
    }

    @PostMapping
    public Map<String, String> create(
            @RequestBody Map<String,
                    String> message) {
        message.put("id", String.valueOf(counter++));
        this.collection_data.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(
            @PathVariable String id,
            @RequestBody Map<String, String> message) {
        Map<String, String> collection_data_fromDB = getMessage(id);
        collection_data_fromDB.putAll(message);
        collection_data_fromDB.put("id", id);
        return collection_data_fromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        this.collection_data.remove(message);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return this.collection_data
                .stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
