package com.erenduzova.ticketary.dto.model.response;

import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;

import java.time.LocalDateTime;

public class TravelResponse {

    private VehicleType vehicleType;
    private City fromCity;
    private City toCity;
    private long fareCents;
    private LocalDateTime travelDate;
    private TravelStatus travelStatus;

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

    public TravelStatus getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(TravelStatus travelStatus) {
        this.travelStatus = travelStatus;
    }

    @Override
    public String toString() {
        return "TravelResponse{" +
                "vehicle=" + vehicleType +
                ", from=" + fromCity +
                ", to=" + toCity +
                ", fareCents=" + fareCents +
                ", Date=" + travelDate +
                ", Status=" + travelStatus +
                '}';
    }
}
