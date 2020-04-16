package com.microservicecours.dao;

import com.microservicecours.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT c FROM Course c, CourseRegistration cr WHERE c.id = cr.course.id AND cr.id=:id_reg")
    List<Course> courseUtilisateur(@Param("id_reg") int id_reg);

    @Query(value = "SELECT c FROM Course c, Category cat WHERE c.category.id = cat.id and cat.id = :id_cat")
    List<Course> getCoursesByCategory(@Param("id_cat") int id_cat);
}
