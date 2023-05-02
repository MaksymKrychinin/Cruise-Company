package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserorderRepository extends JpaRepository<Userorder, Integer> {

    List<Userorder> findAllByIdUserEquals(User user);

    List<Userorder> findAllByIdUserEquals(Integer integer);

    Optional<Userorder> findById(Integer id);

    Userorder save(Userorder entity);

    void delete(Userorder entity);
}