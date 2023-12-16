package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseShipRepository extends JpaRepository<CruiseShip, Integer>, JpaSpecificationExecutor<CruiseShip> {


    Page<CruiseShip> findAll(Specification<CruiseShip> spec, Pageable pageable);

    @Query(value = "select cs from CruiseShip as cs where cs.orderedSeats<cs.capacity")
    Page<CruiseShip> findAllWhereOrderedSeatsLessThanCapacity(Pageable pageable);

    @Override
    Optional<CruiseShip> findById(Integer id);

    @Override
    @Transactional
    <S extends CruiseShip> S save(S entity);

    @Modifying
    @Transactional
    @Query(value = "update CruiseShip cs set cs.orderedSeats = cs.orderedSeats + 1 where cs.id = :id")
    void updateCruiseShipOrderedSeatsPlusOne(Integer id);

    @Override
    @Transactional
    void deleteById(Integer id);

    @Transactional
    void deleteAllByEndDateLessThan(Date date);

    List<CruiseShip> findAllByEndDateLessThan(Date date);
}