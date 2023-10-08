package com.micro.i113_events.repository;

import com.micro.i113_events.model.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthdayRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findAll();

    List<EventEntity> findAllByNotifyIsTrue();

    List<EventEntity> findAllByUserEntity(EventEntity userEntity);

    void deleteAllByUserEntity(EventEntity userEntity);

    void deleteById(int id);

}
