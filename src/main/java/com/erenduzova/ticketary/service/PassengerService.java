package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.PassengerConverter;
import com.erenduzova.ticketary.dto.model.request.PassengerRequest;
import com.erenduzova.ticketary.dto.model.request.TicketRequest;
import com.erenduzova.ticketary.entity.Passenger;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.exception.UserNotFoundException;
import com.erenduzova.ticketary.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerConverter passengerConverter;

    // Create Passenger
    public Passenger create(PassengerRequest passengerRequest) {
        Passenger newPassenger = passengerConverter.convert(passengerRequest);
        passengerRepository.save(newPassenger);
        return newPassenger;
    }

    // Get Passenger By Phone
    public Passenger getByPhone(String phone) {
        return passengerRepository.findByPhone(phone)
                .orElseThrow(() -> new UserNotFoundException("Passenger not found with this phone: " + phone));
    }

    // Get or create ( if not exist ) Passenger By Phone
    public Passenger getOrCreate(PassengerRequest passengerRequest) {
        Optional<Passenger> foundPassenger = passengerRepository.findByPhone(passengerRequest.getPhone());
        return foundPassenger.orElseGet(() -> create(passengerRequest));
    }

    // Convert passengerRequestList to passengerList by using getOrCreate method
    public List<Passenger> getOrCreatePassengers(List<PassengerRequest> passengerRequestList) {
        List<Passenger> passengerList = new ArrayList<>();
        for (PassengerRequest passengerRequest: passengerRequestList) {
            Passenger passenger = getOrCreate(passengerRequest);
            passengerList.add(passenger);
        }
        return passengerList;
    }

    public List<TicketRequest> createTicketRequest(List<Passenger> passengerList, User buyer, Travel travel) {
        return passengerConverter.convertToTicketRequestList(passengerList, buyer, travel);
    }
}
