package com.github.sivaone.service;


import com.github.sivaone.dao.UserRepository;
import com.github.sivaone.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String id) {
        UUID userId = UUID.fromString(id);
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
