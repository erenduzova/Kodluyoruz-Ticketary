package com.erenduzova.ticketarypaymentservice.entity;

import com.erenduzova.ticketarypaymentservice.entity.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName="id")
    private Account account;
    @Column(name = "amount_cents")
    private long amountCents;
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    public Payment() {
    }

    public Payment(Account account, long amountCents, PaymentStatus paymentStatus) {
        this.account = account;
        this.amountCents = amountCents;
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
