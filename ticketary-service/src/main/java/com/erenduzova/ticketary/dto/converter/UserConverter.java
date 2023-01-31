package com.erenduzova.ticketary.dto.converter;

import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private TicketConverter ticketConverter;

    public UserResponse convert(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setGender(user.getGender());
        userResponse.setType(user.getType());
        userResponse.setBoughtTickets(ticketConverter.convert(user.getBoughtTickets()));
        userResponse.setRegistrationDate(user.getRegistrationDate());
        userResponse.setAccountNumber(user.getAccountNumber());
        return userResponse;
    }

    public User convert(UserRequest userRequest, String hashedPassword) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword(hashedPassword);
        user.setGender(userRequest.getGender());
        user.setType(userRequest.getType());
        user.setBoughtTickets(List.of());
        user.setRegistrationDate(LocalDateTime.now());
        user.setAccountNumber(userRequest.getAccountNumber());
        return user;
    }

    public List<UserResponse> convert(List<User> userList) {
        return userList.stream().map(this::convert).toList();
    }
}
