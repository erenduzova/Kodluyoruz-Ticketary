package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.TicketConverter;
import com.erenduzova.ticketary.dto.model.request.PassengerRequest;
import com.erenduzova.ticketary.dto.model.request.TicketRequest;
import com.erenduzova.ticketary.dto.model.response.TicketResponse;
import com.erenduzova.ticketary.entity.Passenger;
import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private PassengerService passengerService;

    // Create Ticket
    public Ticket create(TicketRequest ticketRequest) {
        Ticket newTicket = ticketConverter.convert(ticketRequest);
        ticketRepository.save(newTicket);
        return newTicket;
    }

    // Get All Tickets
    public List<TicketResponse> getAll() {
        return ticketConverter.convert(ticketRepository.findAll());
    }

    public List<TicketResponse> createTickets(Long buyerId, Long travelId, List<PassengerRequest> passengerRequestList) {
        // Find buyer and travel
        User buyer = userService.findById(buyerId);
        Travel travel = travelService.getTravelById(travelId);
        // TODO: Check req. for buying
        // INDIVIDUAL user can buy max 5 ticket for same travel
        // INDIVIDUAL user can buy max 2 MALE ticket in 1 ORDER
        // CORPORATE user can buy max 20 ticket for same travel
        // TODO: Payment

        // Get or create passengers from passenger requests
        List<Passenger> passengerList = passengerService.getOrCreatePassengers(passengerRequestList);
        // Create ticket request from passenger
        List<TicketRequest> ticketRequestList = passengerService.createTicketRequest(passengerList, buyer, travel);
        // Create ticket from ticket request and save the ticket
        List<Ticket> ticketList = ticketRequestList.stream().map(this::create).toList();
        return ticketConverter.convert(ticketList);
    }

    // INDIVIDUAL user can buy max 5 ticket for same travel
    public boolean limitIndividualTravel() {
        return true;
    }

    // INDIVIDUAL user can buy max 2 MALE ticket in 1 ORDER
    public boolean limitIndividualOrderMale() {
        return true;
    }

    // CORPORATE user can buy max 20 ticket for same travel
    public boolean limitCorporateTravel() {
        return true;
    }

}
