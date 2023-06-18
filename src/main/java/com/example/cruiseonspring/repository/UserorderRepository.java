package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserorderRepository extends JpaRepository<UserOrder, Integer> {

    List<UserOrder> findAllByUserEmail(String email);

    @Override
    Optional<UserOrder> findById(Integer id);

    @Override
    void deleteById(Integer id);

    @Override
    @Transactional
    <S extends UserOrder> S save(S entity);
}