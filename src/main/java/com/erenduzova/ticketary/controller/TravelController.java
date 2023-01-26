package com.erenduzova.ticketary.controller;

import com.erenduzova.ticketary.dto.model.request.TravelRequest;
import com.erenduzova.ticketary.dto.model.response.AdminTravelResponse;
import com.erenduzova.ticketary.dto.model.response.TravelResponse;
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

    // Get Travel Response ( Basic Users )
    @GetMapping(value = "/{travelId}")
    public ResponseEntity<TravelResponse> getById(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.getById(travelId));
    }

    // Get Travels ( Basic Users )
    @GetMapping
    public ResponseEntity<List<TravelResponse>> getAll() {
        return ResponseEntity.ok(travelService.getAll());
    }


    // Get Travel Response ( Admin Users )
    @GetMapping(value = "/detailed/{travelId}")
    public ResponseEntity<AdminTravelResponse> getByIdAdmin(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.getByIdAdmin(travelId));
    }

    // Get Travels ( Admin Users )
    @GetMapping(value = "/detailed")
    public ResponseEntity<List<AdminTravelResponse>> getAllAdmin() {
        return ResponseEntity.ok(travelService.getAllAdmin());
    }

    // Cancel Travel
    @PutMapping(value = "/{travelId}")
    public ResponseEntity<AdminTravelResponse> cancelTravel(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.cancel(travelId));
    }


}
