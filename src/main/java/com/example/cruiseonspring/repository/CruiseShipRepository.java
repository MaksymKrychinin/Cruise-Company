package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseShipRepository extends JpaRepository<CruiseShip, Integer> {
    @Query(value = "select cs from CruiseShip as cs where cs.orderedSeats<cs.capacity")
    List<CruiseShip> findAllWhereOrderedSeatsLessThanCapacity();

    @Override
    Optional<CruiseShip> findById(Integer id);

    @Override
    CruiseShip save(CruiseShip cruiseShip);

    @Override
    void deleteById(Integer id);
}