package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.TravelConverter;
import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.entity.enums.VehicleType;
import com.erenduzova.ticketary.exception.TravelNotFoundException;
import com.erenduzova.ticketary.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TravelConverter travelConverter;

    // Create, add new travel
    public TravelResponse create(TravelRequest travelRequest) {
        Travel newTravel = travelConverter.convert(travelRequest);
        travelRepository.save(newTravel);
        return travelConverter.convert(newTravel);
    }

    // Get Travel By Id
    public Travel getTravelById(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new TravelNotFoundException("Travel not found with this id: " + travelId));
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
    // TODO: active before ?, Check status before buying ticket. If cancelled return money.
    public AdminTravelResponse cancel(Long travelId) {
        Travel travel = getTravelById(travelId);
        travel.setTravelStatus(TravelStatus.CANCELLED);
        return travelConverter.convertAdmin(travel);
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
}
