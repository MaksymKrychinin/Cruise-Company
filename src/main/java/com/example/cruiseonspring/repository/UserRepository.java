package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    <S extends User> S save(S entity);

    Optional<User> findByEmail(String email);
}