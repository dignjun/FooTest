package com.example.invok.bean;

import java.io.Serializable;

public class User implements Serializable { // 需要和服务端的包名和类名完全相同，否则报类找不到异常

    private static final long serialVersionUID = -6970967506712260305L;

    private String name;
    private int age;
    private String email;

    //Getter and Setter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", email=" + email + "]";
    }
}
