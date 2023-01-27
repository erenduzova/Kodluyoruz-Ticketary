package com.erenduzova.ticketary.repository;

import com.erenduzova.ticketary.entity.Travel;
import com.erenduzova.ticketary.entity.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findAllByToCity(City searchedCity);

    List<Travel> findAllByFromCity(City searchedCity);
}
