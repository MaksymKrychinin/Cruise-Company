package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseShipRepository extends JpaRepository<CruiseShip, Integer> {
    @Query(value = "select cs from CruiseShip as cs where cs.orderedSeats<cs.capacity")
    List<CruiseShip> findAllWhereOrderedSeatsLessThanCapacity();

    @Override
    Optional<CruiseShip> findById(Integer id);

    @Override
    @Transactional
    <S extends CruiseShip> S save(S entity);

    @Modifying
    @Query(value = "update CruiseShip cs set cs.orderedSeats = cs.orderedSeats + 1 where cs.id = :id")
    CruiseShip updateCruiseShipOrderedSeatsPlusOne(Integer id);

    @Override
    @Transactional
    void deleteById(Integer id);

    @Transactional
    int deleteAllByEndDateLessThan(Date date);

    List<CruiseShip> findAllByEndDateLessThan(Date date);
}