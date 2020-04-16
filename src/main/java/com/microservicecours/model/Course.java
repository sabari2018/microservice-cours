package com.microservicecours.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.Api;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    private String description;

    private String author;
    private Date date;
    private String format;
    private String link;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations = new HashSet<>();

    public Course() {
    }

    public Course(String name, String description, String author, Date date, String format, String link, Category category) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.date = date;
        this.format = format;
        this.link = link;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addRegistration(CourseRegistration registration){
        registrations.add(registration);
        registration.setCourse(this);
    }
}
