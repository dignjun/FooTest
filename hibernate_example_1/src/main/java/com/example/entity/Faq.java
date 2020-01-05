package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Entity
@Table(name = "kb_faq")
public class Faq {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "com.example.generate.UUID")
    private String id;

    private String question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
