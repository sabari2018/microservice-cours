package com.microservicecours.dao;

import com.microservicecours.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT r FROM Role r")
    List<Role> mesRoles();
}
