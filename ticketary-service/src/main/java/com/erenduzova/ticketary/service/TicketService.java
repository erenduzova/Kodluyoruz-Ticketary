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
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.UserType;
import com.erenduzova.ticketary.exception.FailedRequirementException;
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

    // Create And Buy Tickets
    public List<TicketResponse> createTickets(Long buyerId, Long travelId, List<PassengerRequest> passengerRequestList) {
        // Find buyer and travel
        User buyer = userService.findById(buyerId);
        Travel travel = travelService.getTravelById(travelId);
        if (!TravelStatus.ACTIVE.equals(travel.getTravelStatus())) {
            throw new RuntimeException("Travel Status must be active for to buy ticket");
        }
        // Check Requirements
        checkRequirements(buyer, travel, passengerRequestList);
        // TODO: Payment

        // Get or create passengers from passenger requests
        List<Passenger> passengerList = passengerService.getOrCreatePassengers(passengerRequestList);
        // Create ticket request from passenger
        List<TicketRequest> ticketRequestList = passengerService.createTicketRequest(passengerList, buyer, travel);
        // Create ticket from ticket request and save the ticket
        List<Ticket> ticketList = ticketRequestList.stream().map(this::create).toList();
        return ticketConverter.convert(ticketList);
    }

    // Check requirements if anything fails throw FailedRequirementException
    private void checkRequirements(User buyer, Travel travel, List<PassengerRequest> passengerRequestList) {
        if (UserType.INDIVIDUAL.equals(buyer.getType())) {
            if (!limitIndividualOrderMale(passengerRequestList)) {
                throw new FailedRequirementException("Individual user male passenger limit for order is: " + INDIVIDUAL_ORDER_LIMIT_MALE);
            }
            if (!limitIndividualTravel(passengerRequestList, buyer.getId(), travel.getId())) {
                throw new FailedRequirementException("Individual user passenger limit for travel is: " + INDIVIDUAL_TRAVEL_LIMIT);
            }
        } else {
            if (!limitCorporateTravel(passengerRequestList, buyer.getId(), travel.getId())) {
                throw new FailedRequirementException("Corporate user ticket limit for a travel is: " + CORPORATE_TRAVEL_LIMIT);
            }
        }
    }

    // INDIVIDUAL user can buy max 5 ticket for same travel
    public boolean limitIndividualTravel(List<PassengerRequest> passengerRequestList, Long buyerId, Long travelId) {
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
    public boolean limitCorporateTravel(List<PassengerRequest> passengerRequestList, Long buyerId, Long travelId) {
        long boughtTicketCount = getBoughtTicketsByUserIdAndTravelId(buyerId, travelId).size();
        long buyerLimit = CORPORATE_TRAVEL_LIMIT - boughtTicketCount;
        return passengerRequestList.size() <= buyerLimit;
    }

}
