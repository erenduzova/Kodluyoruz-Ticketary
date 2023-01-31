package com.erenduzova.ticketarypaymentservice.repository;

import com.erenduzova.ticketarypaymentservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(long accountNumber);
}
