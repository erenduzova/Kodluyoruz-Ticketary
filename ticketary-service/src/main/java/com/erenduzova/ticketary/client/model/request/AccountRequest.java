package com.erenduzova.ticketary.client.model.request;

public class AccountRequest {

    private long accountNumber;

    public AccountRequest() {
    }

    public AccountRequest(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
