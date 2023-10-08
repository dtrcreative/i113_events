package com.micro.i113_events.repository;

import com.micro.i113_events.model.entity.EventEntity;
import com.micro.i113_events.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findAll();

    List<EventEntity> findAllByNotifyIsTrue();

    List<EventEntity> findAllByUserEntity(UserEntity userEntity);

    void deleteAllByUserEntity(EventEntity userEntity);

    void deleteById(int id);

}
