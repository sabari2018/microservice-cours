package com.microservicecours.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void addCourse(Course course){
        courses.add(course);
        course.setCategory(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

}
