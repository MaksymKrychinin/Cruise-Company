package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByPasswordAndEmail(String password, String email);
}