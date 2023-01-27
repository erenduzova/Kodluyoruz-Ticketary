package com.erenduzova.ticketary.dto.model.request;

import com.erenduzova.ticketary.entity.Passenger;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.User;

public class TicketRequest {

    private User buyer;
    private Travel travel;
    private Passenger passenger;

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
