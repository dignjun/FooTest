package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Entity
@Table(name = "kb_val")
public class Value {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "com.example.generate.UUID")
    private String id;

    private String question;

    /**
     * 添加关联关系一对多的关联关系
     * @return
     */
    @OneToMany(mappedBy = "value", targetEntity = Faq.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Faq> faqs;

    public Set<Faq> getFaqs() {
        return faqs;
    }

    public void setFaq(Set<Faq> faqs) {
        this.faqs = faqs;
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
        return "Value{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
