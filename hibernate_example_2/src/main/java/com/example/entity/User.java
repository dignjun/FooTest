package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DINGJUN on 2019.12.15.
 */
//@Entity
//@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Role.class, mappedBy = "id")
    private Set<Role> rows;

    public Set<Role> getRows() {
        return rows;
    }

    public void setRows(Set<Role> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
