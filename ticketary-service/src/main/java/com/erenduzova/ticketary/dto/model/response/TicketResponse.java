package com.erenduzova.ticketary.dto.model.response;

public class TicketResponse {

    private Long id;
    private PassengerTicketResponse passengerTicketResponse;
    private TravelResponse travelResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PassengerTicketResponse getPassengerTicketResponse() {
        return passengerTicketResponse;
    }

    public void setPassengerTicketResponse(PassengerTicketResponse passengerTicketResponse) {
        this.passengerTicketResponse = passengerTicketResponse;
    }

    public TravelResponse getTravelResponse() {
        return travelResponse;
    }

    public void setTravelResponse(TravelResponse travelResponse) {
        this.travelResponse = travelResponse;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                ", travelResponse=" + travelResponse +
                '}';
    }
}
