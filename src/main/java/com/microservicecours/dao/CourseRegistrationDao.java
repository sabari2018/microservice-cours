package com.microservicecours.dao;

import com.microservicecours.model.Course;
import com.microservicecours.model.CourseRegistration;
import com.microservicecours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRegistrationDao extends JpaRepository<CourseRegistration, Integer> {


   // SELECT c.id, c.name FROM db_cours.course c, db_cours.course_registration cr, db_cours.user u WHERE c.id = cr.course_id and u.id = cr.user_id and u.id=1

    @Query(value = "SELECT cr FROM CourseRegistration cr WHERE cr.user.id=:id_user")
    List<CourseRegistration> userCourses(@Param("id_user") int id_user);

    List<CourseRegistration> findAllByUser(@Param("user") User user);
}
