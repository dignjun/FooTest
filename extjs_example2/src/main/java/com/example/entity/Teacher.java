package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by DINGJUN on 2019.09.25.
 */
@Entity
@Table(name = "teacher")
public class Teacher {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
