package com.erenduzova.ticketarypaymentservice.dto.converter;

import com.erenduzova.ticketarypaymentservice.dto.model.request.AccountRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.AccountResponse;
import com.erenduzova.ticketarypaymentservice.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountConverter {

    private final long ACCOUNT_START_BALANCE = 10000;

    public Account convert(AccountRequest accountRequest) {
        Account newAccount = new Account();
        newAccount.setAccountNumber(accountRequest.getAccountNumber());
        newAccount.setBalance(ACCOUNT_START_BALANCE);
        return newAccount;
    }

    public AccountResponse convert(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setBalance(account.getBalance());
        return accountResponse;
    }

    public List<AccountResponse> convert(List<Account> accountList) {
        return accountList.stream().map(this::convert).toList();
    }


}
