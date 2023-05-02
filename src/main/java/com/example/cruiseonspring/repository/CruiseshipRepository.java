package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.Cruiseship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseshipRepository extends JpaRepository<Cruiseship, Integer> {
    List<Cruiseship> findAllByCapacityGreaterThanEqual(int capacity);

    Optional<Cruiseship> findById(Integer integer);

    Cruiseship save(Cruiseship cruiseship);

    void deleteById(Integer integer);
}