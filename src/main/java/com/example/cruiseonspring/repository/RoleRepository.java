package com.example.cruiseonspring.repository;

import com.example.cruiseonspring.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role getById(Integer id);
}