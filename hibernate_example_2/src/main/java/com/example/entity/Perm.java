package com.example.entity;

import javax.persistence.*;

/**
 * Created by DINGJUN on 2019.12.15.
 */
//@Entity
//@Table(name = "perm")
public class Perm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
