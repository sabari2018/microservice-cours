package com.microservicecours.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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

    public void addUser(User user){
        this.users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.setRole(null);
    }

    public boolean hasRole(User user){
        return this.users.contains(user);
    }
}
