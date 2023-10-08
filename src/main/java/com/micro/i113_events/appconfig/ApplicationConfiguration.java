package com.micro.i113_events.appconfig;

import com.micro.i113_events.service.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

public class ApplicationConfiguration {

    @Bean
    public MessageService getMessageService(MessageSource messageSource) {
        return new MessageService(messageSource);
    }

}
