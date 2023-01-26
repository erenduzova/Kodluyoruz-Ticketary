package com.erenduzova.ticketary.dto.model.response;

import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdminTravelResponse {

    private Long id;
    private VehicleType vehicleType;
    private City fromCity;
    private City toCity;
    private int capacity;
    private List<Ticket> soldTickets;
    private long soldTicketsCount;
    private long fareCents;
    private BigDecimal totalIncome;
    private LocalDateTime travelDate;
    private TravelStatus travelStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(List<Ticket> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public long getSoldTicketsCount() {
        return soldTicketsCount;
    }

    public void setSoldTicketsCount(long soldTicketsCount) {
        this.soldTicketsCount = soldTicketsCount;
    }

    public long getFareCents() {
        return fareCents;
    }

    public void setFareCents(long fareCents) {
        this.fareCents = fareCents;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
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
}
