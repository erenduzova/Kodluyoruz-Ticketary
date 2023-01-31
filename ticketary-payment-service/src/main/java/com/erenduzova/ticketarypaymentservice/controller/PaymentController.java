package com.erenduzova.ticketarypaymentservice.controller;

import com.erenduzova.ticketarypaymentservice.dto.model.request.PaymentRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.PaymentResponse;
import com.erenduzova.ticketarypaymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make Payment
    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.makePayment(paymentRequest);
    }

    // Get All Payments
    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAll();
    }

}
