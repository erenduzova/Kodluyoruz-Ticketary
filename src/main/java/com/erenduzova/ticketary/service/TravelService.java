package com.erenduzova.ticketary.service;

import com.erenduzova.ticketary.dto.converter.TravelConverter;
import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.enums.TravelStatus;
import com.erenduzova.ticketary.exception.TravelNotFoundException;
import com.erenduzova.ticketary.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private Travel getTravelById(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new TravelNotFoundException("Travel not found with this id: " + travelId));
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


}
