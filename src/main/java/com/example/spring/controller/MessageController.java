package com.example.spring.controller;

import com.example.spring.dto.MessageResponse;
import com.example.spring.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public MessageResponse getMessage() {
        return messageService.getMessage();
    }

    @GetMapping("with")
    public MessageResponse getMessage(@RequestParam("value") String msg) {
        return messageService.getMessage(msg);
    }
}
