package com.micro.i113_events.appconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ApplicationInfo {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.ip}")
    private String serverIp;

    @Value("${app.version}")
    private String version;

    @Value("${app.delimiter}")
    private String delimiter;

    private final String ln = "\n";
    private final Date startTime = new Date();

    @EventListener(ApplicationReadyEvent.class)
    public void printAppData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Application info:").append(ln);
        builder.append(delimiter).append(ln);
        builder.append("Application Name: ").append(appName).append(ln);
        builder.append("Application Version: ").append(version).append(ln);
        builder.append("Configuration: ").append(activeProfile).append(ln);
        builder.append("Server port: ").append(serverPort).append(ln);
        builder.append("Server ip: ").append(serverIp).append(ln);
        builder.append("Start date time: ").append(new Date()).append(ln);
        builder.append(delimiter);
        log.warn(builder.toString());
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent contextClosedEvent) {
        StringBuilder builder = new StringBuilder();
        builder.append("Application info:").append(ln);
        builder.append(delimiter).append(ln);
        builder.append("Start date time: ").append(new Date(contextClosedEvent.getTimestamp())).append(ln);
        builder.append(calculateUpTime(contextClosedEvent.getTimestamp())).append(ln);
        builder.append(delimiter);
        log.warn(builder.toString());
    }

    private StringBuilder calculateUpTime(long endMilisec) {
        long fullUpTime = (endMilisec - startTime.getTime()) / 1000;
        final long days = fullUpTime / 60 / 60 / 24;
        fullUpTime = fullUpTime - (days * 24 * 60 * 60);
        final long hours = fullUpTime / 60 / 60;
        fullUpTime = fullUpTime - (hours * 60 * 60);
        final long minutes = fullUpTime / 60;
        fullUpTime = fullUpTime - (hours * 60);
        final long seconds = fullUpTime;
        StringBuilder builder = new StringBuilder();
        builder.append("Full up time : ");
        builder.append(days).append(" days, ");
        builder.append(hours).append(" hours, ");
        builder.append(minutes).append(" minutes, ");
        builder.append(seconds).append(" seconds, ");
        return builder;
    }
}
