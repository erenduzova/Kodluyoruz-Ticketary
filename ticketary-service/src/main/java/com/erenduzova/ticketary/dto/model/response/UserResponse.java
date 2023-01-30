package com.erenduzova.ticketary.dto.model.response;

import com.erenduzova.ticketary.entity.enums.Gender;
import com.erenduzova.ticketary.entity.enums.UserType;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;
    private UserType type;
    private List<TicketResponse> boughtTickets;
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<TicketResponse> getBoughtTickets() {
        return boughtTickets;
    }

    public void setBoughtTickets(List<TicketResponse> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
