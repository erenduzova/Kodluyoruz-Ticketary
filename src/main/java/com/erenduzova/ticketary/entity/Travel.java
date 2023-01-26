package com.erenduzova.ticketary.entity;

import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "travels")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;
    @Column(name = "from_city")
    private City fromCity;
    @Column(name = "to_city")
    private City toCity;
    @Column(name = "capacity")
    private int capacity;
    @OneToMany(mappedBy = "travel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> soldTickets;
    @Column(name = "fare_cents")
    private long fareCents;
    @Column(name = "travel_date")
    private LocalDateTime travelDate;
    @Column(name = "travel_status")
    private TravelStatus travelStatus;

    public Travel() {
    }

    public Travel(VehicleType vehicleType, City fromCity, City toCity, int capacity, long fareCents, LocalDateTime travelDate) {
        this.vehicleType = vehicleType;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.capacity = capacity;
        this.fareCents = fareCents;
        this.travelDate = travelDate;
    }

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
}
