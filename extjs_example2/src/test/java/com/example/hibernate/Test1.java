package com.example.hibernate;

import com.example.App;
import com.example.entity.Class;
import com.example.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

/**
 * Created by DINGJUN on 2019.09.25.
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Test
    public void test1() {
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        Class cls = new Class();
        cls.setId(1);
        cls.setName("cls 1");
        HashSet<Student> students = new HashSet<>();
        students.add(student);
        cls.setStudents(students);
        student.setCls(cls);
        hibernateTemplate.saveOrUpdate(student);
    }

}
