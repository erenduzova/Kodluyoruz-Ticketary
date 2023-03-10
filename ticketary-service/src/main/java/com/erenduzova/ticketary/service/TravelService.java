package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.client.PaymentServiceClient;
import com.erenduzova.ticketary.client.model.enums.PaymentStatus;
import com.erenduzova.ticketary.client.model.request.PaymentRequest;
import com.erenduzova.ticketary.client.model.response.PaymentResponse;
import com.erenduzova.ticketary.dto.converter.TravelConverter;
import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.Ticket;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;
import com.erenduzova.ticketary.exception.PaymentFailedException;
import com.erenduzova.ticketary.exception.TicketaryServiceException;
import com.erenduzova.ticketary.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TravelConverter travelConverter;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    private final Logger logger = Logger.getLogger(TravelService.class.getName());

    // Create, add new travel
    public TravelResponse create(TravelRequest travelRequest) {
        Travel newTravel = travelConverter.convert(travelRequest);
        travelRepository.save(newTravel);
        return travelConverter.convert(newTravel);
    }

    // Get Travel By Id
    public Travel getTravelById(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new TicketaryServiceException("Travel not found with this id: " + travelId));
    }

    // Get Travels By toCity
    private List<Travel> getTravelsByToCity(City searchedCity) {
        return travelRepository.findAllByToCity(searchedCity);
    }

    // Get Travels By fromCity
    private List<Travel> getTravelsByFromCity(City searchedCity) {
        return travelRepository.findAllByFromCity(searchedCity);
    }

    // Get Travels By Vehicle Type
    private List<Travel> getTravelsByVehicleType(VehicleType searchedVehicle) {
        return travelRepository.findAllByVehicleType(searchedVehicle);
    }

    // Get TravelResponse By Id
    public TravelResponse getById(Long travelId) {
        return travelConverter.convert(getTravelById(travelId));
    }

    // Get All Travel Responses
    public List<TravelResponse> getAll() {
        return travelConverter.convert(travelRepository.findAll());
    }

    // Get TravelResponse By Id - Admin
    public AdminTravelResponse getByIdAdmin(Long travelId) {
        return travelConverter.convertAdmin(getTravelById(travelId));
    }

    // Get All Travel Responses - Admin
    public List<AdminTravelResponse> getAllAdmin() {
        return travelConverter.convertAdmin(travelRepository.findAll());
    }

    // Cancel travel
    public AdminTravelResponse cancel(Long travelId) {
        Travel travel = getTravelById(travelId);
        if (!TravelStatus.ACTIVE.equals(travel.getTravelStatus())) {
            logger.log(Level.WARNING, "[cancel] - travel cancellation failed: {0}", travel.getTravelStatus());
            throw new TicketaryServiceException("Travel must be in active status to cancel.");
        }
        travel.setTravelStatus(TravelStatus.CANCELLED);
        travelRepository.save(travel);
        returnMoney(travel.getSoldTickets());
        return travelConverter.convertAdmin(travel);
    }

    // Return money of the canceled travel
    private void returnMoney(List<Ticket> soldTickets) {
        soldTickets.forEach(ticket -> {
            PaymentRequest paymentRequest = new PaymentRequest(ticket.getUser().getAccountNumber(), -ticket.getTravel().getFareCents());
            PaymentResponse paymentResponse = paymentServiceClient.makePayment(paymentRequest);
            if (PaymentStatus.FAILED.equals(paymentResponse.getPaymentStatus())) {
                logger.log(Level.WARNING, "[returnMoney] - payment failed: {0}", paymentResponse.getPaymentStatus());
                throw new PaymentFailedException("Payment Failed!");
            }
        });
    }

    // Get Travels By City
    public List<TravelResponse> searchByCity(City searchedCity) {
        List<Travel> travelsFromCity = getTravelsByFromCity(searchedCity);
        List<Travel> travelsToCity = getTravelsByToCity(searchedCity);
        List<Travel> searchedTravels = Stream.concat(travelsFromCity.stream(), travelsToCity.stream()).toList();
        return travelConverter.convert(searchedTravels);
    }

    // Get Travels By Vehicle Type
    public List<TravelResponse> searchByVehicleType(VehicleType searchedVehicle) {
        return travelConverter.convert(getTravelsByVehicleType(searchedVehicle));
    }

    // Get Travels By Date Time
    public List<TravelResponse> searchByDateTime(LocalDateTime searchedDateTime) {
        return travelConverter.convert(travelRepository.findAllByTravelDate(searchedDateTime));
    }
}
