package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.client.PaymentServiceClient;
import com.erenduzova.ticketary.client.model.request.AccountRequest;
import com.erenduzova.ticketary.client.model.request.NotificationRequest;
import com.erenduzova.ticketary.client.model.response.AccountResponse;
import com.erenduzova.ticketary.configuration.RabbitMQConfiguration;
import com.erenduzova.ticketary.dto.converter.UserConverter;
import com.erenduzova.ticketary.dto.model.request.LoginRequest;
import com.erenduzova.ticketary.dto.model.request.UserRequest;
import com.erenduzova.ticketary.dto.model.response.UserResponse;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.exception.TicketaryServiceException;
import com.erenduzova.ticketary.repository.UserRepository;
import com.erenduzova.ticketary.util.PasswordUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfiguration rabbitMQConfiguration;

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    // Create And Save New User
    public UserResponse create(UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        registeredUserControl(userRequest);
        String hashedPassword = PasswordUtil.hashPassword(userRequest.getPassword());
        User newUser = userConverter.convert(userRequest, hashedPassword);
        userRepository.save(newUser);
        logger.log(Level.INFO, "[create] - user created: {0}", newUser.getId());
        AccountResponse accountResponse = paymentServiceClient.create(new AccountRequest(userRequest.getAccountNumber()));
        UserResponse userResponse = userConverter.convert(newUser);
        NotificationRequest notificationRequest = new NotificationRequest("New User Registered " + userResponse.toString(), newUser.getEmail());
        rabbitTemplate.convertAndSend(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getQueueName() , notificationRequest);
        return userResponse;
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
                .orElseThrow(() -> new TicketaryServiceException("User not found with this id: " + userId));
    }

    // Get User By Email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new TicketaryServiceException("User not found with this email: " + email));
    }

    // Get UserResponse By Id
    public UserResponse getResponseById(Long userId) {
        return userConverter.convert(findById(userId));
    }

    // Check Email Uniqueness Before Creating User
    private void registeredUserControl(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new TicketaryServiceException("User already registered with this email: " + userRequest.getEmail());
        }
    }

}
