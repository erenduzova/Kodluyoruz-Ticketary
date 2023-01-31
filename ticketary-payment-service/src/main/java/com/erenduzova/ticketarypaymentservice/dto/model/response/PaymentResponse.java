package com.erenduzova.ticketarypaymentservice.dto.model.response;

import com.erenduzova.ticketarypaymentservice.entity.enums.PaymentStatus;

public class PaymentResponse {

    private long accountNumber;
    private long amountCents;
    private PaymentStatus paymentStatus;

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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
