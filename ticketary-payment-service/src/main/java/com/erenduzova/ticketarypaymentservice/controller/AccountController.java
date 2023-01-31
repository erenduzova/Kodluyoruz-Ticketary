package com.erenduzova.ticketarypaymentservice.controller;

import com.erenduzova.ticketarypaymentservice.dto.model.request.AccountRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.AccountResponse;
import com.erenduzova.ticketarypaymentservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create Account
    @PostMapping
    public AccountResponse create(@RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    // Get AccountResponse By Account Number
    @GetMapping(value = "/{accountNumber}")
    public AccountResponse getByAccountNum(@PathVariable long accountNumber){
        return accountService.getAccountResponseByAccountNumber(accountNumber);
    }

}
