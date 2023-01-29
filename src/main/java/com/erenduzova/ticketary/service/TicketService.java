package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.TicketConverter;
import com.erenduzova.ticketary.dto.model.request.PassengerRequest;
import com.erenduzova.ticketary.dto.model.request.TicketRequest;
import com.erenduzova.ticketary.dto.model.response.TicketResponse;
import com.erenduzova.ticketary.entity.Passenger;
import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.User;
import com.erenduzova.ticketary.entity.enums.Gender;
import com.erenduzova.ticketary.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final long INDIVIDUAL_TRAVEL_LIMIT = 5;
    private final long INDIVIDUAL_ORDER_LIMIT_MALE = 2;
    private final long CORPORATE_TRAVEL_LIMIT = 20;

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

    // Get User's All Bought Ticket Responses
    public List<TicketResponse> getAllByUserId(Long buyerId) {
        User buyer = userService.findById(buyerId);
        return ticketConverter.convert(buyer.getBoughtTickets());
    }

    // Get User's All Bought Tickets
    public List<Ticket> getBoughtTicketsByUserId(Long buyerId) {
        User buyer = userService.findById(buyerId);
        return buyer.getBoughtTickets();
    }

    // Get User's All Bought Tickets
    public List<Ticket> getBoughtTicketsByUserIdAndTravelId(Long buyerId, Long travelId) {
        User buyer = userService.findById(buyerId);
        Travel travel = travelService.getTravelById(travelId);
        return buyer.getBoughtTickets().stream().filter(ticket -> travel.equals(ticket.getTravel())).toList();
    }

    public List<TicketResponse> createTickets(Long buyerId, Long travelId, List<PassengerRequest> passengerRequestList) {
        // Find buyer and travel
        User buyer = userService.findById(buyerId);
        Travel travel = travelService.getTravelById(travelId);
        // TODO: Check req. for buying
        // checkRequirements(buyerId, travelId, passengerRequestList);
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
    public boolean limitIndividualTravel(List<PassengerRequest> passengerRequestList, Long buyerId, Long travelId) {
        // TODO: check all tickets this user bought for this travel
        long boughtTicketCount = getBoughtTicketsByUserIdAndTravelId(buyerId, travelId).size();
        long buyerLimit = INDIVIDUAL_TRAVEL_LIMIT - boughtTicketCount;
        return passengerRequestList.size() <= buyerLimit;
    }

    // INDIVIDUAL user can buy max 2 MALE ticket in 1 ORDER
    public boolean limitIndividualOrderMale(List<PassengerRequest> passengerRequestList) {
        long maleCount = passengerRequestList.stream()
                .filter(passengerRequest -> Gender.MALE.equals(passengerRequest.getGender()))
                .count();
        return maleCount <= INDIVIDUAL_ORDER_LIMIT_MALE;
    }

    // CORPORATE user can buy max 20 ticket for same travel
    public boolean limitCorporateTravel(List<PassengerRequest> passengerRequestList) {
        return passengerRequestList.size() <= CORPORATE_TRAVEL_LIMIT;
    }

}
