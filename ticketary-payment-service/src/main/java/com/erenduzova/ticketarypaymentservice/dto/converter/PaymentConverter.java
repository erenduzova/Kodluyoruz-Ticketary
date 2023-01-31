package com.erenduzova.ticketarypaymentservice.dto.converter;

import com.erenduzova.ticketarypaymentservice.dto.model.request.PaymentRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.PaymentResponse;
import com.erenduzova.ticketarypaymentservice.entity.Account;
import com.erenduzova.ticketarypaymentservice.entity.Payment;
import com.erenduzova.ticketarypaymentservice.entity.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentConverter {

    public Payment convert(PaymentRequest paymentRequest, Account account) {
        Payment newPayment = new Payment();
        newPayment.setAccount(account);
        newPayment.setAmountCents(paymentRequest.getAmountCents());
        newPayment.setPaymentStatus(PaymentStatus.FAILED);
        return newPayment;
    }

    public PaymentResponse convert(Payment payment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setAccountNumber(payment.getAccount().getAccountNumber());
        paymentResponse.setAmountCents(payment.getAmountCents());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        return paymentResponse;
    }

    public List<PaymentResponse> convert(List<Payment> paymentList) {
        return paymentList.stream().map(this::convert).toList();
    }
}
