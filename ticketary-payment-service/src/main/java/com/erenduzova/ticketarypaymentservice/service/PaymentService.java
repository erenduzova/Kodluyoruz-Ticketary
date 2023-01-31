package com.erenduzova.ticketarypaymentservice.service;

import com.erenduzova.ticketarypaymentservice.dto.converter.PaymentConverter;
import com.erenduzova.ticketarypaymentservice.dto.model.request.PaymentRequest;
import com.erenduzova.ticketarypaymentservice.dto.model.response.PaymentResponse;
import com.erenduzova.ticketarypaymentservice.entity.Account;
import com.erenduzova.ticketarypaymentservice.entity.Payment;
import com.erenduzova.ticketarypaymentservice.entity.enums.PaymentStatus;
import com.erenduzova.ticketarypaymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentConverter paymentConverter;

    // Make Payment
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        Account account = accountService.getByAccountNumber(paymentRequest.getAccountNumber());
        Payment newPayment = paymentConverter.convert(paymentRequest, account);
        if (account.getBalance() >= paymentRequest.getAmountCents()) {
            accountService.exchange(newPayment.getAccount(), newPayment.getAmountCents());
            newPayment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
            paymentRepository.save(newPayment);
        }
        return paymentConverter.convert(newPayment);
    }

    // Get All Payments
    public List<PaymentResponse> getAll() {
        return paymentConverter.convert(paymentRepository.findAll());
    }


}
