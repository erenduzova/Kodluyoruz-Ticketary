package com.erenduzova.ticketary.dto.converter;

import com.erenduzova.ticketary.dto.model.request.TicketRequest;
import com.erenduzova.ticketary.dto.model.response.TicketResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketConverter {

    @Autowired
    private PassengerConverter passengerConverter;


    public Ticket convert(TicketRequest ticketRequest) {
        Ticket newTicket = new Ticket();
        newTicket.setUser(ticketRequest.getBuyer());
        newTicket.setTravel(ticketRequest.getTravel());
        newTicket.setSeatNumber(findSeat(newTicket.getTravel()));
        newTicket.setPassenger(ticketRequest.getPassenger());
        return newTicket;
    }

    public TicketResponse convert(Ticket ticket) {
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setId(ticket.getId());
        ticketResponse.setTravelResponse(convertTravelResponse(ticket.getTravel()));
        ticketResponse.setPassengerTicketResponse(passengerConverter.convertPassengerTicketResponse(ticket.getPassenger()));
        ticketResponse.setSeatNumber(ticket.getSeatNumber());
        return ticketResponse;
    }
    public List<TicketResponse> convert(List<Ticket> ticketList) {
        return ticketList.stream().map(this::convert).toList();
    }

    // Return seat number from max capacity to 1
    private int findSeat(Travel travel) {
        return travel.getCapacity() - travel.getSoldTickets().size();
    }

    private TravelResponse convertTravelResponse(Travel travel) {
        TravelResponse travelResponse = new TravelResponse();
        travelResponse.setVehicleType(travel.getVehicleType());
        travelResponse.setFromCity(travel.getFromCity());
        travelResponse.setToCity(travel.getToCity());
        travelResponse.setFareCents(travel.getFareCents());
        travelResponse.setTravelDate(travel.getTravelDate());
        return travelResponse;
    }

}
