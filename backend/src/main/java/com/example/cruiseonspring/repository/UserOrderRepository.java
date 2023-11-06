package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer>, CrudRepository<UserOrder, Integer> {

    List<UserOrder> findAllByUserEmail(String email, Pageable pageRequest);

    @Override
    Optional<UserOrder> findById(Integer id);

    @Override
    @Transactional
    void deleteById(Integer id);

    @Override
    @Transactional
    <S extends UserOrder> S save(S entity);
}