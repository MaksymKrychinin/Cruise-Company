package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.Cruiseship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseshipRepository extends JpaRepository<Cruiseship, Integer> {
    @Query(value = "SELECT cs.* FROM Cruiseships as cs where cs.ordered_seats<cs.capacity", nativeQuery = true)
    List<Cruiseship> findAllWhereOrderedSeatsLessThanCapacity();

    Optional<Cruiseship> findById(Integer integer);

    Cruiseship save(Cruiseship cruiseship);

    void deleteById(Integer integer);
}