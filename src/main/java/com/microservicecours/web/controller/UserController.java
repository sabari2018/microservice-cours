package com.microservicecours.web.controller;

import com.microservicecours.dao.RoleDao;
import com.microservicecours.dao.UserDao;
import com.microservicecours.model.Role;
import com.microservicecours.model.User;
import com.microservicecours.web.exception.AddUserException;
import com.microservicecours.web.exception.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Gestion des Utilisateurs")
@RestController
public class UserController {

    @Autowired
    UserDao userDao;


    @ApiOperation(value = "Retourne la liste des Utilisateurs")
    @GetMapping(value = "/users")
    public List<User> getUsers(){

        List<User> users = userDao.findAll();
        if (users.isEmpty()) throw new UserNotFoundException("Aucun utilisateur dans la base");

        return users;
    }

    @ApiOperation(value = "Retourne un Utilisateur selon son ID")
    @GetMapping(value = "/users/{id}")
    public Optional<User> getUsers(@PathVariable int id){

        Optional<User> user = userDao.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("L'utilisateur avec l'ID "+ id +" n'existe pas");

        return user;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user){

        user.getRole().addUser(user);

        User newUser = userDao.save(user);
        if (newUser == null) throw new AddUserException("Impossible d'ajouter cet utilisateur");

        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }
}
