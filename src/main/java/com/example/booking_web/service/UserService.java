package com.example.booking_web.service;

import com.example.booking_web.entity.User;
import com.example.booking_web.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById (Long id){
        return userRepository.findById(id).orElse(null);
    }
}
