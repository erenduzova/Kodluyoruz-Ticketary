package com.erenduzova.ticketary.client.model.request;

public class PaymentRequest {

    private long accountNumber;
    private long amountCents;

    public PaymentRequest() {
    }

    public PaymentRequest(long accountNumber, long amountCents) {
        this.accountNumber = accountNumber;
        this.amountCents = amountCents;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public long getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(long amountCents) {
        this.amountCents = amountCents;
    }
}
