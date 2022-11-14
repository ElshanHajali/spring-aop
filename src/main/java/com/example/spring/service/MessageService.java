package com.example.spring.service;

import com.example.spring.aop.annotation.LoggingExecution;
import com.example.spring.dto.MessageResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@LoggingExecution
public class MessageService {

    @LoggingExecution
    public MessageResponse getMessage() {
        someLogic();
        return new MessageResponse("Hello World!");
    }

    public MessageResponse getMessage(String message) {
        return new MessageResponse(message);
    }

    @SneakyThrows
    public void someLogic() {
        Thread.sleep(new Random().nextInt(1000));
    }
}
