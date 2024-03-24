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
import org.joda.time.LocalDate;
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

    public int createByListAndCountSuccessful(List<BirthdayDto> unitsDtoList) {
        UserEntity userEntity = userService.findOrCreateUser(unitsDtoList.get(0).getUserId());
        List<BirthdayEntity> baseEntities = repository.findAllByUserEntity(userEntity);
        List<BirthdayEntity> inputList = converter.convertDtoToEntities(unitsDtoList);
        int counter = 0;
        for (BirthdayEntity inputEntity : inputList) {
            if(!isExist(baseEntities, inputEntity)){
                repository.save(inputEntity);
                counter++;
            }
        }
        return counter;
    }

    public int replaceListAndCount(List<BirthdayDto> unitsDtoList) {
        deleteAllUserRelated(unitsDtoList.get(0).getUserId());
        repository.saveAllAndFlush(converter.convertDtoToEntities(unitsDtoList));
        return unitsDtoList.size();
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

    public void deleteSelected(List<Integer> selected) {
        repository.deleteAllById(selected);
    }

    public void deleteAllUserRelated(String username) {
        UserEntity user = userService.findOrCreateUser(username);
        List<BirthdayEntity> entityList = repository.findAllByUserEntity(user);
        if (entityList.size() > 0) {
            repository.deleteAll(entityList);
        }
    }

    private boolean isExist(List<BirthdayEntity> inputList, BirthdayEntity inputEntity){
        for(BirthdayEntity entity: inputList){
            if(entity.compareTo(inputEntity) == 0){
                return true;
            }
        }
        return false;
    }

    public List<BirthdayDto> getTodayBirthdaysTest(){
        return converter.convertEntitiesToDto(findTodayBirthdays());
    }

    public ArrayList<BirthdayEntity> findTodayBirthdays(){
        List<BirthdayEntity> units = repository.findAllByNotifyIsTrue();
        ArrayList<BirthdayEntity> todayUnits = new ArrayList<>();
        LocalDate today = new LocalDate();
        for(BirthdayEntity entity:units){
            LocalDate date = new LocalDate(entity.getDate());
            if(today.getMonthOfYear() == date.getMonthOfYear()){
                if(today.getDayOfMonth() == date.getDayOfMonth()){
                    todayUnits.add(entity);
                }
            }
        }
        return todayUnits;
    }
}
