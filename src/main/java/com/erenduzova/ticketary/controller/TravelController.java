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

    // Create Travel
    // TODO: Only Admins can create or add travel
    @PostMapping
    public ResponseEntity<TravelResponse> create(@RequestBody TravelRequest travelRequest) {
        return ResponseEntity.ok(travelService.create(travelRequest));
    }

    // Get Travels ( Basic Users )
    @GetMapping
    public ResponseEntity<List<TravelResponse>> getAll() {
        return ResponseEntity.ok(travelService.getAll());
    }

    // Get Travels ( Admin Users )
    @GetMapping(value = "/detailed")
    public ResponseEntity<List<AdminTravelResponse>> getAllAdmin() {
        return ResponseEntity.ok(travelService.getAllAdmin());
    }

    // Cancel Travel TODO: Admin Only
    @PutMapping(value = "/{travelId}")
    public ResponseEntity<AdminTravelResponse> cancelTravel(@PathVariable Long travelId) {
        return ResponseEntity.ok(travelService.cancel(travelId));
    }


}
