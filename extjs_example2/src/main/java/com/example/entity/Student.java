package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DINGJUN on 2019.09.25.
 */
@Entity
@Table(name = "student")
public class Student {
    private int id;
    private String name;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER, optional = true, targetEntity = Class.class)
    @JoinColumn(name = "c_id")
    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    private Class cls;

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
