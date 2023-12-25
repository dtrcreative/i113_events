package com.micro.i113_events.service.converter;

import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.service.UserService;
import com.micro.i113_events.service.utils.DateCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EventConverter {

    private UserService userService;
    private DateCalculator calculator;

    public List<EventDto> convertEntitiesToDto(List<EventEntity> entitiesList) {
        return entitiesList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EventEntity> convertDtoToEntities(List<EventDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public EventDto convertEntityToDto(EventEntity inputEntity) {
        return EventDto.builder()
                .id(inputEntity.getId())
                .userId(inputEntity.getUserEntity().getUserId())
                .eventName(inputEntity.getEventName())
                .date(inputEntity.getDate())
                .daysLeft(calculator.countDaysBetweenToday(inputEntity.getDate()))
                .notify(inputEntity.isNotify())
                .description(inputEntity.getDescription())
                .build();
    }

    public EventEntity convertDtoToEntity(EventDto inputDTO) {
        return EventEntity.builder()
                .userEntity(userService.findOrCreateUser(inputDTO.getUserId()))
                .eventName(inputDTO.getEventName())
                .date(inputDTO.getDate())
                .notify(inputDTO.isNotify())
                .description(inputDTO.getDescription())
                .build();
    }

}
