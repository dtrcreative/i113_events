package com.micro.i113_events.service.sheduler;

import com.micro.i113_events.model.dto.TelegramDto;
import com.micro.i113_events.model.entity.BirthdayEntity;
import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.model.entity.UserEntity;
import com.micro.i113_events.service.BirthdayService;
import com.micro.i113_events.service.EventService;
import com.micro.i113_events.service.converter.BirthdayConverter;
import com.micro.i113_events.service.converter.EventConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramShedule {

    private BirthdayService birthdayService;
    private EventService eventService;

    private BirthdayConverter birthdayConverter;
    private EventConverter eventConverter;

    @Value("${services.api}")
    private String api;
    @Value("${services.telegram.default-url}")
    private String telegram;

    @Value("${services.telegram.message.headerBirthdays}")
    private String headerBirthdays;
    @Value("${services.telegram.message.headerEvents}")
    private String headerEvents;

    public TelegramShedule(BirthdayService birthdayService, EventService eventService, BirthdayConverter birthdayConverter, EventConverter eventConverter) {
        this.birthdayService = birthdayService;
        this.eventService = eventService;
        this.birthdayConverter = birthdayConverter;
        this.eventConverter = eventConverter;
    }

    public void action() {
        HashMap<UserEntity, ArrayList<EventEntity>> eventsMap =
                eventConverter.mapUsersEvents(eventService.findTodayEvents());
        HashMap<UserEntity, ArrayList<BirthdayEntity>> birthdayMap =
                birthdayConverter.mapUsersBirthdays(birthdayService.findTodayBirthdays());

        ArrayList<TelegramDto> outputList = new ArrayList<>();
        outputList.addAll(prepareEventsDto(eventsMap));
        outputList.addAll(prepareBirthdaysDto(birthdayMap));

        if (outputList.size() > 0) {
            sendToTelegram(outputList);
        }
    }

    private ArrayList<TelegramDto> prepareEventsDto(HashMap<UserEntity, ArrayList<EventEntity>> eventsMap) {
        ArrayList<TelegramDto> resultDto = new ArrayList<>();
        for (Map.Entry<UserEntity, ArrayList<EventEntity>> entry : eventsMap.entrySet()) {
            StringBuilder builder = new StringBuilder();
            builder.append(headerEvents);
            for (EventEntity entity : entry.getValue()) {
                builder.append("\n")
                        .append(entity.getEventName());
            }
            resultDto.add(TelegramDto.builder()
                    .userId(entry.getKey().getUserId())
                    .message(builder.toString())
                    .build());
        }
        return resultDto;
    }

    private ArrayList<TelegramDto> prepareBirthdaysDto(HashMap<UserEntity, ArrayList<BirthdayEntity>> eventsMap) {
        ArrayList<TelegramDto> resultDto = new ArrayList<>();
        for (Map.Entry<UserEntity, ArrayList<BirthdayEntity>> entry : eventsMap.entrySet()) {
            StringBuilder builder = new StringBuilder();
            builder.append(headerBirthdays);
            for (BirthdayEntity entity : entry.getValue()) {
                builder.append("\n")
                        .append(entity.getFirstName())
                        .append(" ")
                        .append(entity.getLastName());
            }
            resultDto.add(TelegramDto.builder()
                    .userId(entry.getKey().getUserId())
                    .message(builder.toString())
                    .build());
        }
        return resultDto;
    }


    private void sendToTelegram(ArrayList<TelegramDto> dtos) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ArrayList<TelegramDto>> request = new HttpEntity<>(dtos);
        restTemplate.postForObject(api + telegram, request, TelegramDto.class);
    }


}
