package com.micro.i113_events.service;

import com.micro.i113_events.model.MessageDto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service

public class MessageService {

    private final MessageSource messageSource;

//    @Value("${telegram-service-url}")
//    private String telegramUrl;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public void sendMessageToTelegram(MessageDto messageDto) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto);
//        restTemplate.postForObject(telegramUrl, request, MessageDto.class);
    }

}
