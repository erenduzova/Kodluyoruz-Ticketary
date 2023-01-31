package com.erenduzova.ticketarypaymentservice.repository;

import com.erenduzova.ticketarypaymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
