package com.erenduzova.ticketary.repository;

import com.erenduzova.ticketary.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
}
