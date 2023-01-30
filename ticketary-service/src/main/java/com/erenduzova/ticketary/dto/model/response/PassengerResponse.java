package com.erenduzova.ticketary.dto.model.response;

import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.enums.Gender;

import java.util.List;

public class PassengerResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private Gender gender;
    private List<Ticket> ticketList;

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

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
