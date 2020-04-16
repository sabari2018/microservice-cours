package com.microservicecours.web.controller;

import com.microservicecours.dao.CourseDao;
import com.microservicecours.dao.CourseRegistrationDao;
import com.microservicecours.dao.UserDao;
import com.microservicecours.model.Course;
import com.microservicecours.model.CourseRegistration;
import com.microservicecours.model.User;
import com.microservicecours.web.exception.CourseNotFoundException;
import com.microservicecours.web.exception.CourseRegistrationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Gestion des inscriptons aux Cours")
@RestController
public class CourseRegistrationController {

    @Autowired
    CourseRegistrationDao registrationDao;

    @Autowired
    UserDao userDao;

    @ApiOperation(value = "Inscription à un cours")
    @PostMapping(value = "/registration")
    public ResponseEntity<CourseRegistration> register(@RequestBody CourseRegistration registration){

        CourseRegistration newRegistration = registrationDao.save(registration);
        if (newRegistration == null) throw new CourseRegistrationException("Impossible de s'enregistrer");

        return new ResponseEntity<CourseRegistration>(registration, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deinscription à un cours")
    @DeleteMapping(value = "/course_registration")
    public void unregister(@RequestBody CourseRegistration registration){
        registrationDao.delete(registration);
    }

    @ApiOperation(value = "Liste de cours d'un utilisateur")
    @GetMapping(value = "/registration/{id_user}")
    public List<CourseRegistration> getUserCourse(@PathVariable int id_user){

        List<CourseRegistration> registration =  registrationDao.userCourses(id_user);

        if (registration.isEmpty()) throw new CourseNotFoundException("Cet utilisateur n'a aucun cours");

        return registration;
    }
}
