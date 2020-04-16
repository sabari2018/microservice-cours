package com.microservicecours.web.controller;


import com.microservicecours.dao.RoleDao;
import com.microservicecours.model.Role;
import com.microservicecours.web.exception.AddRoleException;
import com.microservicecours.web.exception.RoleNoFoundException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Gestion des Roles")
@RestController
public class RoleController {

    @Autowired
    RoleDao roleDao;

    @GetMapping(value="/roles")
    public List<Role> getRoles(){

       // List<Role> roles = roleDao.findAll();
        List<Role> roles = roleDao.mesRoles();

        if (roles.isEmpty()) throw new RoleNoFoundException("Aucun role disponible dans la base");

        return roles;
    }

    @GetMapping(value = "/roles/{id}")
    public Optional<Role> getRole(@PathVariable int id){

        Optional<Role> role = roleDao.findById(id);
        if (role.isEmpty()) throw new RoleNoFoundException("Le role avec l'ID "+ id + " n'existe pas");

        return role;
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role){

        Role newRole = roleDao.save(role);
        if (newRole == null) throw new AddRoleException("Impossible d'ajouter ce Role");

        return new ResponseEntity<Role>(role, HttpStatus.CREATED);
    }
}
