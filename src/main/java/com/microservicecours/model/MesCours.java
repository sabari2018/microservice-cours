package com.microservicecours.model;

public class MesCours {

    private Integer id;
    private Course course;

    public MesCours(Integer id, Course course) {
        this.id = id;
        this.course = course;
    }

    public MesCours() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
