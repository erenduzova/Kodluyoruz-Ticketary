package com.erenduzova.ticketary.client;

import com.erenduzova.ticketary.client.model.request.AccountRequest;
import com.erenduzova.ticketary.client.model.request.PaymentRequest;
import com.erenduzova.ticketary.client.model.response.AccountResponse;
import com.erenduzova.ticketary.client.model.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "ticketary-payment-service", url = "localhost:8081")
public interface PaymentServiceClient {

    @PostMapping(value = "/payments")
    PaymentResponse makePayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping(value = "/payments")
    List<PaymentResponse> getAllPayments();

    @PostMapping(value = "/accounts")
    AccountResponse create(@RequestBody AccountRequest accountRequest);

    @GetMapping(value = "/accounts/{accountNumber}")
    AccountResponse getByAccountNum(@PathVariable long accountNumber);

}
