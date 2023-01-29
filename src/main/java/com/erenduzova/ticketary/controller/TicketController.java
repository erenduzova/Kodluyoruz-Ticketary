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
    // TODO: Add Admin control
    // TODO: id returns null
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    // Get User's Bought Tickets
    @GetMapping(value = "/{buyerId}")
    public List<TicketResponse> getAllByUserId(@PathVariable Long buyerId) {
        return ticketService.getAllByUserId(buyerId);
    }

    // TODO: Seat Number returns faulty ( all order gets same seat number )
    // TODO: id returns null
    // TODO: check travel in corporate users buy requirement for max 20 ticket
    // Create Tickets
    @PostMapping(value = "/{buyerId}/{travelId}")
    public ResponseEntity<List<TicketResponse>> createTickets(@PathVariable Long buyerId,
                                                              @PathVariable Long travelId,
                                                              @RequestBody List<PassengerRequest> passengerRequestList) {
        return ResponseEntity.ok(ticketService.createTickets(buyerId, travelId, passengerRequestList));
    }


}
