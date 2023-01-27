package com.erenduzova.ticketary.dto.converter;

import com.erenduzova.ticketary.dto.model.request.PassengerRequest;
import com.erenduzova.ticketary.dto.model.response.PassengerResponse;
import com.erenduzova.ticketary.dto.model.response.PassengerTicketResponse;
import com.erenduzova.ticketary.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerConverter {


    public Passenger convert(PassengerRequest passengerRequest) {
        Passenger newPassenger = new Passenger();
        newPassenger.setFirstName(passengerRequest.getFirstName());
        newPassenger.setLastName(passengerRequest.getLastName());
        newPassenger.setGender(passengerRequest.getGender());
        newPassenger.setTicketList(List.of());
        return newPassenger;
    }

    public PassengerResponse convert(Passenger passenger) {
        PassengerResponse passengerResponse = new PassengerResponse();
        passengerResponse.setFirstName(passenger.getFirstName());
        passengerResponse.setLastName(passenger.getLastName());
        passengerResponse.setGender(passenger.getGender());
        passengerResponse.setTicketList(passenger.getTicketList());
        return passengerResponse;
    }

    public List<PassengerResponse> convert(List<Passenger> passengerList) {
        return passengerList.stream().map(this::convert).toList();
    }

    public PassengerTicketResponse convertTicketResponse(Passenger passenger) {
        PassengerTicketResponse passengerTicketResponse = new PassengerTicketResponse();
        passengerTicketResponse.setFirstName(passenger.getFirstName());
        passengerTicketResponse.setLastName(passenger.getLastName());
        passengerTicketResponse.setGender(passenger.getGender());
        return passengerTicketResponse;
    }

}
