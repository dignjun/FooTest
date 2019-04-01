package com.example.hash;

/**
 * 测试类
 * @author DINGJUN
 * @date 2019.04.01
 */
public class Employee {
    private String name;
    private double salary;
    private int seniority;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && name.equals(((Employee)obj).name);
    }

    // getter and setter
    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getSeniority() {
        return seniority;
    }
}
