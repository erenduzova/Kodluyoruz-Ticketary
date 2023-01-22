package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.UserConverter;
import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    // Create And Save New User
    public UserResponse create(UserRequest userRequest) {
        User newUser = userConverter.convert(userRequest);
        userRepository.save(newUser);
        return userConverter.convert(newUser);
    }

    // Get All Users
    public List<UserResponse> getAll() {
        return userConverter.convert(userRepository.findAll());
    }

    // Get User By Id
    // TODO: Edit Exception
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with this id: " + userId));
    }

    // Get UserResponse By Id
    public UserResponse getResponseById(Long userId) {
        return userConverter.convert(findById(userId));
    }
}
