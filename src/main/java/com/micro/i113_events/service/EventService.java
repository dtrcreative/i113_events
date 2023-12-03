package com.micro.i113_events.service;

import com.micro.i113_events.exception.EventException;
import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.model.entity.BirthdayEntity;
import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.model.entity.UserEntity;
import com.micro.i113_events.repository.EventRepository;
import com.micro.i113_events.service.converter.EventConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository repository;
    private EventConverter converter;
    private UserService userService;

    public List<EventDto> getAll(String userId) {
        UserEntity userEntity = userService.findOrCreateUser(userId);
        List<EventEntity> entitiesList = repository.findAllByUserEntity(userEntity);
        if (entitiesList.size() > 0) {
            return converter.convertEntitiesToDto(entitiesList);
        }
        return new ArrayList<>();
    }

    public EventDto create(EventDto unitDto) {
        EventEntity entity = repository.save(converter.convertDtoToEntity(unitDto));
        return converter.convertEntityToDto(entity);
    }

    public EventDto update(EventDto unitDto) {
        Optional<EventEntity> entity = repository.findById(unitDto.getId());
        if (entity.isPresent()) {
            EventEntity updatedEntity = converter.convertDtoToEntity(unitDto);
            updatedEntity.setId(entity.get().getId());
            updatedEntity = repository.save(updatedEntity);
            return converter.convertEntityToDto(updatedEntity);
        }
        throw new EventException("Update error", HttpStatus.BAD_REQUEST);
    }

    public void delete(int id) {
        Optional<EventEntity> entity = repository.findById(id);
        entity.ifPresent(foundedEntity -> repository.delete(foundedEntity));
    }

    public void deleteSelected(List<Integer> selected) {
        repository.deleteAllById(selected);
    }

    public void deleteAll(String username) {
        UserEntity user = userService.findOrCreateUser(username);
        List<EventEntity> entityList = repository.findAllByUserEntity(user);
        if (entityList.size() > 0) {
            repository.deleteAll(entityList);
        }
    }

}
