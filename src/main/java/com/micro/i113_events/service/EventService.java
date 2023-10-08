package com.micro.i113_events.service;

import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.model.entity.UserEntity;
import com.micro.i113_events.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    private EventRepository repository;
    private UserService userService;

    public List<EventDto> getAll(String userId) {
        UserEntity userEntity = userService.findOrCreateUser(userId);
        List<EventEntity> entitiesList = repository.findAllByUserEntity(userEntity);
        if (entitiesList.size() > 0) {
//            return converter.convertEntitiesToDto(entitiesList);
        }
        return new ArrayList<>();
    }
}
