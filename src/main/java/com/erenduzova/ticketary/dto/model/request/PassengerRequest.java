package com.erenduzova.ticketary.dto.model.request;

import com.erenduzova.ticketary.entity.enums.Gender;

public class PassengerRequest {
    private String firstName;
    private String lastName;
    private Gender gender;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
