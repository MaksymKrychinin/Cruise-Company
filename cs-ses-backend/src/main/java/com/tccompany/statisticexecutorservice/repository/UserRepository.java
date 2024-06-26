package com.tccompany.statisticexecutorservice.repository;

import com.tccompany.statisticexecutorservice.entity.jms.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAll();

    User save(User subscriber);
}
