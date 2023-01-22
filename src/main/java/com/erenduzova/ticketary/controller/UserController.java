package com.erenduzova.ticketary.controller;

import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    // Get All Users
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }
}
