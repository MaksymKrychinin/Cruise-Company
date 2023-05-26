package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserorderRepository extends JpaRepository<UserOrder, Integer> {

    List<UserOrder> findAllByUser(User user);

    @Override
    Optional<UserOrder> findById(Integer id);

    @Override
    UserOrder save(UserOrder entity);

    @Override
    void deleteById(Integer id);
}