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

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    // Get User By Id
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> getResponseById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getResponseById(userId));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // Login
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

}
