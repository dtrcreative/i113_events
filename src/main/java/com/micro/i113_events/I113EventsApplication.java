package com.micro.i113_events;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Events"))
public class I113EventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(I113EventsApplication.class, args);
    }

}
