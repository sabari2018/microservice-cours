package com.microservicecours.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class CourseRegistration {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime registeredAt;

    private String status;

    public CourseRegistration() {
    }

    public CourseRegistration(User user, Course course, LocalDateTime registeredAt, String status) {
        this.user = user;
        this.course = course;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public CourseRegistration(Integer id,Course course) {
        this.id = id;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
