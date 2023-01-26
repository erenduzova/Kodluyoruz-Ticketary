package com.erenduzova.ticketary.dto.converter;

import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TravelConverter {

    public Travel convert(TravelRequest travelRequest) {
        Travel travel = new Travel();
        travel.setVehicleType(travelRequest.getVehicleType());
        travel.setFromCity(travelRequest.getFromCity());
        travel.setToCity(travelRequest.getToCity());
        travel.setCapacity(findCapacity(travelRequest.getVehicleType()));
        travel.setSoldTickets(List.of());
        travel.setFareCents(travelRequest.getFareCents());
        travel.setTravelDate(travelRequest.getTravelDate());
        travel.setTravelStatus(TravelStatus.ACTIVE);
        return travel;
    }

    public TravelResponse convert(Travel travel) {
        TravelResponse travelResponse = new TravelResponse();
        travelResponse.setVehicleType(travel.getVehicleType());
        travelResponse.setFromCity(travel.getFromCity());
        travelResponse.setToCity(travel.getToCity());
        travelResponse.setFareCents(travel.getFareCents());
        travelResponse.setTravelDate(travel.getTravelDate());
        return travelResponse;
    }

    public List<TravelResponse> convert(List<Travel> travelList) {
        return travelList.stream().map(this::convert).toList();
    }



    public AdminTravelResponse convertAdmin(Travel travel) {
        AdminTravelResponse adminTravelResponse = new AdminTravelResponse();
        adminTravelResponse.setId(travel.getId());
        adminTravelResponse.setVehicleType(travel.getVehicleType());
        adminTravelResponse.setFromCity(travel.getFromCity());
        adminTravelResponse.setToCity(travel.getToCity());
        adminTravelResponse.setCapacity(travel.getCapacity());
        adminTravelResponse.setSoldTickets(travel.getSoldTickets());
        adminTravelResponse.setSoldTicketsCount(travel.getSoldTickets().size());
        adminTravelResponse.setFareCents(travel.getFareCents());
        adminTravelResponse.setTotalIncome(findTotalIncome(travel.getFareCents(), travel.getSoldTickets().size()));
        adminTravelResponse.setTravelDate(travel.getTravelDate());
        adminTravelResponse.setTravelStatus(travel.getTravelStatus());
        return adminTravelResponse;
    }

    public List<AdminTravelResponse> convertAdmin(List<Travel> travelList) {
        return travelList.stream().map(this::convertAdmin).toList();
    }

    // Find Capacity By VehicleType
    private int findCapacity(VehicleType vehicleType) {
        // Smallest by default (BUS capacity = 45)
        int vehicleCapacity = 45;
        if (vehicleType.equals(VehicleType.PLANE)) {
            vehicleCapacity = 189;
        }
        return vehicleCapacity;
    }

    // Find Total Income
    private BigDecimal findTotalIncome(long fareCents, long soldTicketCount) {
        long totalCents = soldTicketCount * fareCents;
        return new BigDecimal(totalCents).movePointLeft(2);
    }

}
