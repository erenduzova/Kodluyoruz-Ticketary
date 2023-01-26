package com.erenduzova.ticketary.dto.model.response;

import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.VehicleType;

import java.time.LocalDateTime;

public class TravelResponse {

    private VehicleType vehicleType;
    private City fromCity;
    private City toCity;
    private long fareCents;
    private LocalDateTime travelDate;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public long getFareCents() {
        return fareCents;
    }

    public void setFareCents(long fareCents) {
        this.fareCents = fareCents;
    }

    public LocalDateTime getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDateTime travelDate) {
        this.travelDate = travelDate;
    }
}
