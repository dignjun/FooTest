package com.example.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by DINGJUN on 2019.09.25.
 */
@Table(name = "course")
@Entity
public class Course {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
