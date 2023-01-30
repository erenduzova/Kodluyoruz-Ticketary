package com.erenduzova.ticketary.controller;

import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
import com.erenduzova.ticketary.entity.enums.City;
import com.erenduzova.ticketary.entity.enums.VehicleType;
import com.erenduzova.ticketary.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    // TODO: Make Role Based Auth. ( Admin And Normal Users )

    // Create Travel
    @PostMapping
    public ResponseEntity<TravelResponse> create(@RequestBody TravelRequest travelRequest) {
        return ResponseEntity.ok(travelService.create(travelRequest));
    }

    // Get Travel Response By Id ( Basic Users )
    @GetMapping(value = "/{travelId}")
    public ResponseEntity<TravelResponse> getById(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.getById(travelId));
    }

    // Get All Travels ( Basic Users )
    @GetMapping
    public ResponseEntity<List<TravelResponse>> getAll() {
        return ResponseEntity.ok(travelService.getAll());
    }


    // Get Travel Response By Id ( Admin Users )
    @GetMapping(value = "/detailed/{travelId}")
    public ResponseEntity<AdminTravelResponse> getByIdAdmin(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.getByIdAdmin(travelId));
    }

    // Get All Travels ( Admin Users )
    @GetMapping(value = "/detailed")
    public ResponseEntity<List<AdminTravelResponse>> getAllAdmin() {
        return ResponseEntity.ok(travelService.getAllAdmin());
    }

    // Cancel Travel
    // TODO: Return money for canceled travels
    @PutMapping(value = "/{travelId}/cancel")
    public ResponseEntity<AdminTravelResponse> cancelTravel(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.cancel(travelId));
    }

    // Search Travel By City
    // TODO : Find better way to format string to enum and use other necessary places
    @GetMapping(value = "/search/city/{cityName}")
    public ResponseEntity<List<TravelResponse>> searchByCity(@PathVariable String cityName) {
        String searchedCity = cityName.replaceAll("\\s", "").toUpperCase();
        return ResponseEntity.ok(travelService.searchByCity(City.valueOf(searchedCity)));
    }

    // Search Travel By Vehicle Type
    @GetMapping(value = "/search/vehicle/{vehicleType}")
    public ResponseEntity<List<TravelResponse>> searchByVehicleType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(travelService.searchByVehicleType(VehicleType.valueOf(vehicleType)));
    }

    // TODO: Add Search Travel By DateTime
    // TODO: Make Travel Status Completed if it's time passed
}
