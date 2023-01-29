package com.erenduzova.ticketary.controller;

import com.erenduzova.ticketary.dto.model.request.LoginRequest;
import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    // TODO: Change returns to ResponseEntity<> !!!
    @Autowired
    private UserService userService;

    // Create User
    // TODO: Add uniqueness control ( email )
    // TODO: Send mail after save
    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.create(userRequest);
    }

    // Get User By Id
    @GetMapping(value = "/{userId}")
    public UserResponse getResponseById(@PathVariable Long userId) {
        return userService.getResponseById(userId);
    }

    // Get All Users
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    // Login
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

}
