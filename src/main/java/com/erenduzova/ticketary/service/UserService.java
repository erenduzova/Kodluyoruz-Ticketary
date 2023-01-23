package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.UserConverter;
import com.erenduzova.ticketary.dto.model.request.LoginRequest;
import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.exception.UserNotFoundException;
import com.erenduzova.ticketary.repository.UserRepository;
import com.erenduzova.ticketary.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    // Create And Save New User
    // TODO: Send mail after save
    public UserResponse create(UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = PasswordUtil.hashPassword(userRequest.getPassword());
        User newUser = userConverter.convert(userRequest, hashedPassword);
        userRepository.save(newUser);
        return userConverter.convert(newUser);
    }

    // User Login
    public String login(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User foundUser = findByEmail(loginRequest.getEmail());
        boolean isValid = PasswordUtil.validatePassword(loginRequest.getPassword(), foundUser.getPassword());
        return isValid ? "Successful Login" : "Login Failed!";
    }

    // Get All Users
    public List<UserResponse> getAll() {
        return userConverter.convert(userRepository.findAll());
    }

    // Get User By Id
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this id: " + userId));
    }

    // Get User By Email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with this email: " + email));
    }

    // Get UserResponse By Id
    public UserResponse getResponseById(Long userId) {
        return userConverter.convert(findById(userId));
    }

}
