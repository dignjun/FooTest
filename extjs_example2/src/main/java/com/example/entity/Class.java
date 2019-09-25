package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by DINGJUN on 2019.09.25.
 */
@Entity
@Table(name = "class")
public class Class {
    private int id;
    private String name;

    @OneToMany(cascade = {}, fetch = FetchType.LAZY, orphanRemoval = false, targetEntity = Student.class, mappedBy = "cls")
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

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
