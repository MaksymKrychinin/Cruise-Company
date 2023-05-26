package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserorderRepository extends JpaRepository<Userorder, Integer> {

    List<Userorder> findAllByUser(User user);

    @Override
    Optional<Userorder> findById(Integer id);

    @Override
    Userorder save(Userorder entity);

    @Override
    void deleteById(Integer id);
}