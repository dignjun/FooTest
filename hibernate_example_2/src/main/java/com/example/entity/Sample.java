package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Entity
@Table(name = "kb_sample")
public class Sample {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "com.example.generate.UUID")
    private String id;
    private String sample;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
