package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Repository
public class ValueDao {
    @Autowired
    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
}
