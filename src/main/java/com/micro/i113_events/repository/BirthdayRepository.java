package com.micro.i113_events.repository;

import com.micro.i113_events.model.entity.BirthdayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthdayRepository extends JpaRepository<BirthdayEntity, Integer> {

    List<BirthdayEntity> findAll();

    List<BirthdayEntity> findAllByNotifyIsTrue();

    List<BirthdayEntity> findAllByUserEntity(BirthdayEntity userEntity);

    void deleteAllByUserEntity(BirthdayEntity userEntity);

    void deleteById(int id);

}
