package com.micro.i113_events.service;

import com.micro.i113_events.exception.EventException;
import com.micro.i113_events.model.dto.BirthdayDto;
import com.micro.i113_events.model.dto.EventDto;
import com.micro.i113_events.model.entity.BirthdayEntity;
import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.model.entity.UserEntity;
import com.micro.i113_events.repository.BirthdayRepository;
import com.micro.i113_events.service.converter.BirthdayConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BirthdayService {

    private BirthdayRepository repository;
    private BirthdayConverter converter;
    private UserService userService;

    public List<BirthdayDto> getAll(String userId) {
        UserEntity userEntity = userService.findOrCreateUser(userId);
        List<BirthdayEntity> entitiesList = repository.findAllByUserEntity(userEntity);
        if (entitiesList.size() > 0) {
            return converter.convertEntitiesToDto(entitiesList);
        }
        return new ArrayList<>();
    }

    public BirthdayDto create(BirthdayDto unitDto) {
        BirthdayEntity entity = repository.save(converter.convertDtoToEntity(unitDto));
        return converter.convertEntityToDto(entity);
    }

    public BirthdayDto update(BirthdayDto unitDto) {
        Optional<BirthdayEntity> entity = repository.findById(unitDto.getId());
        if (entity.isPresent()) {
            BirthdayEntity updatedEntity = converter.convertDtoToEntity(unitDto);
            updatedEntity.setId(entity.get().getId());
            updatedEntity = repository.save(updatedEntity);
            return converter.convertEntityToDto(updatedEntity);
        }
        throw new EventException("Update error", HttpStatus.BAD_REQUEST);
    }

    public void delete(int id) {
        Optional<BirthdayEntity> entity = repository.findById(id);
        entity.ifPresent(foundedEntity -> repository.delete(foundedEntity));
    }

    public void deleteAll(String username) {
        UserEntity user = userService.findOrCreateUser(username);
        List<BirthdayEntity> entityList = repository.findAllByUserEntity(user);
        if (entityList.size() > 0) {
            repository.deleteAll(entityList);
        }
    }
}
