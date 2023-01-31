package com.erenduzova.ticketarypaymentservice.service;

import com.erenduzova.ticketarypaymentservice.dto.converter.AccountConverter;
import com.erenduzova.ticketarypaymentservice.dto.model.request.AccountRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.AccountResponse;
import com.erenduzova.ticketarypaymentservice.entity.Account;
import com.erenduzova.ticketarypaymentservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountConverter accountConverter;

    // Create Account
    public AccountResponse create(AccountRequest accountRequest) {
        Account newAccount = accountConverter.convert(accountRequest);
        accountRepository.save(newAccount);
        return accountConverter.convert(newAccount);
    }

    // Get Account By Account Number
    public Account getByAccountNumber(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found with this accountNumber: " + accountNumber));
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
