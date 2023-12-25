package com.micro.i113_events.service.converter;

import com.micro.i113_events.model.dto.BirthdayDto;
import com.micro.i113_events.model.entity.BirthdayEntity;
import com.micro.i113_events.service.UserService;
import com.micro.i113_events.service.utils.DateCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BirthdayConverter {

    private UserService userService;
    private DateCalculator calculator;

    public List<BirthdayDto> convertEntitiesToDto(List<BirthdayEntity> entitiesList) {
        return entitiesList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<BirthdayEntity> convertDtoToEntities(List<BirthdayDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public BirthdayDto convertEntityToDto(BirthdayEntity inputEntity) {
        return BirthdayDto.builder()
                .id(inputEntity.getId())
                .userId(inputEntity.getUserEntity().getUserId())
                .firstName(inputEntity.getFirstName())
                .lastName(inputEntity.getLastName())
                .date(inputEntity.getDate())
                .daysLeft(calculator.countDaysBetweenToday(inputEntity.getDate()))
                .notify(inputEntity.isNotify())
                .description(inputEntity.getDescription())
                .build();
    }

    public BirthdayEntity convertDtoToEntity(BirthdayDto inputDTO) {
        return BirthdayEntity.builder()
                .userEntity(userService.findOrCreateUser(inputDTO.getUserId()))
                .firstName(inputDTO.getFirstName())
                .lastName(inputDTO.getLastName())
                .date(inputDTO.getDate())
                .notify(inputDTO.isNotify())
                .description(inputDTO.getDescription())
                .build();
    }
}
