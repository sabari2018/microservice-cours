package com.microservicecours.web.controller;

import com.microservicecours.dao.CourseDao;
import com.microservicecours.model.Course;
import com.microservicecours.web.exception.AddCourseException;
import com.microservicecours.web.exception.CourseNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Gestion des modules de cours")
@RestController
public class CourseController {

    @Autowired
    CourseDao courseDao;

    @ApiOperation(value = "Retourne la liste des modules de cours")
    @GetMapping(value = "/courses")
    public List<Course> getCourses(){

        List<Course> courses = courseDao.findAll();

        if (courses.isEmpty()) throw new CourseNotFoundException("Aucun cours disponible");

        return courses;
    }

    @ApiOperation(value = "Retourne un cour selon son ID")
    @GetMapping(value = "/courses/{id}")
    public Optional<Course> getCourse(@PathVariable int id){

        Optional<Course> course = courseDao.findById(id);
        if (course.isEmpty()) throw new CourseNotFoundException("Le cour avec l'ID "+ id + " n'existe pas");

        return course;
    }

    @ApiOperation(value = "Retourne les cours selon une categorie")
    @GetMapping(value = "/courses/category/{id_cat}")
    public List<Course> getCoursesByCategory(@PathVariable int id_cat){

        List<Course> courses = courseDao.getCoursesByCategory(id_cat);
        if (courses.isEmpty()) throw new CourseNotFoundException("Aucun cours disponible pour cette categorie");

        return courses;
    }

    @ApiOperation(value = "Ajouter un cour")
    @PostMapping(value = "/courses")
    public ResponseEntity<Course> addCour(@RequestBody Course course){

        Course newCourse = courseDao.save(course);
        if (newCourse == null) throw new AddCourseException("Imposible d'ajouter ce cour");

        return new ResponseEntity<Course>(course, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Editer un cour")
    @PutMapping(value = "/courses")
    public void updateCourse(@RequestBody Course course){
        courseDao.save(course);
    }

    @ApiOperation(value = "Supprimer un cour")
    @DeleteMapping(value = "/courses")
    public void deleteCourse(@RequestBody Course course){
        courseDao.delete(course);
    }
}
