package com.rest.jwebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("collection")
public class MainRestController {
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
    public List<Map<String, String>> listData() {
        return this.collection_data;
    }
}
