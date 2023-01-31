package com.erenduzova.ticketarypaymentservice.service;

import com.erenduzova.ticketarypaymentservice.dto.converter.AccountConverter;
import com.erenduzova.ticketarypaymentservice.dto.model.request.AccountRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.AccountResponse;
import com.erenduzova.ticketarypaymentservice.entity.Account;
import com.erenduzova.ticketarypaymentservice.exception.TicketaryPaymentServiceException;
import com.erenduzova.ticketarypaymentservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountConverter accountConverter;

    private final Logger logger = Logger.getLogger(AccountService.class.getName());

    // Create Account
    public AccountResponse create(AccountRequest accountRequest) {
        Account newAccount = accountConverter.convert(accountRequest);
        accountRepository.save(newAccount);
        logger.log(Level.INFO, "[create] - account created: {0}", newAccount.getId());
        return accountConverter.convert(newAccount);
    }

    // Get Account By Account Number
    public Account getByAccountNumber(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new TicketaryPaymentServiceException("Account not found with this accountNumber: " + accountNumber));
    }

    // Get AccountResponse By Account Number
    public AccountResponse getAccountResponseByAccountNumber(long accountNumber) {
        return accountConverter.convert(getByAccountNumber(accountNumber));
    }

    // payment ( cents )
    public void exchange(Account account, long amountCents) {
        account.setBalance(account.getBalance() - amountCents);
        accountRepository.save(account);
    }
}
