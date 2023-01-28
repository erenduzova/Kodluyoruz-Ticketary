package com.erenduzova.ticketary.repository;

import com.erenduzova.ticketary.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByPhone(String phone);
}
