package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DINGJUN on 2019.12.15.
 */
//@Entity
//@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "id")
//    @JoinTable(name = "", catalog = "", inverseJoinColumns = {@JoinColumn(name = "")})
    private Set<User> user;

    @OneToMany(mappedBy = "", targetEntity = Perm.class, orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Perm> perms;

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<Perm> getPerms() {
        return perms;
    }

    public void setPerms(Set<Perm> perms) {
        this.perms = perms;
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
