package com.micro.i113_events.service;

import com.micro.i113_events.exception.EventException;
import com.micro.i113_events.model.entity.UserEntity;
import com.micro.i113_events.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public UserEntity findOrCreateUser(String userId) {
        if (Optional.ofNullable(userId).isPresent()) {
            return repository.findUserEntityByUserId(userId).orElseGet(() -> repository.save(new UserEntity(userId)));
        }
        throw new EventException("Missing user id", HttpStatus.BAD_REQUEST);
    }

    public List<UserEntity> getAll() {
        return repository.findAll();
    }
}
