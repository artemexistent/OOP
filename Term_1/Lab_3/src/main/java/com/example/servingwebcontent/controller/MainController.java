package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main( Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String email,  Map<String, Object> model){
        Message message = new Message(name, email);
        Iterable<Message> messages = messageRepo.findAll();
        messageRepo.save(message);
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,  Map<String, Object> model){
        Iterable<Message> messages;

        if (filter!=null && !filter.isEmpty()) {
            messages = messageRepo.findByName(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
}