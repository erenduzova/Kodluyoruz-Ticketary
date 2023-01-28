package com.erenduzova.ticketary.controller;

import com.erenduzova.ticketary.dto.model.request.PassengerRequest;
import com.erenduzova.ticketary.dto.model.response.TicketResponse;
import com.erenduzova.ticketary.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Get All Tickets
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    // Create Tickets
    @PostMapping(value = "/{buyerId}/{travelId}")
    public ResponseEntity<List<TicketResponse>> createTickets(@PathVariable Long buyerId,
                                                              @PathVariable Long travelId,
                                                              @RequestBody List<PassengerRequest> passengerRequestList) {
        return ResponseEntity.ok(ticketService.createTickets(buyerId, travelId, passengerRequestList));
    }


}
