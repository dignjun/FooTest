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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "com.example.generate.UUID")
    private String id;

    private String question;

    /**
     * 添加关联关系，多对一关系
     * 注：如果没有在多的一方指定关联字段，hibernate会自动生成一个关联字段（也就是添加了一个外检约束），这里的案例就是value_id数据库中给的实际上是v_id
     * @return
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Value.class, optional = true)
    @JoinColumn(name = "v_id")
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

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

    @Override
    public String toString() {
        return "Faq{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
