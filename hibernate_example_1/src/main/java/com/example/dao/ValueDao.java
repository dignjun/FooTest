package com.example.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Repository
public class ValueDao extends HibernateDaoSupport {
    private EntityManagerFactory factory;
    private HibernateTemplate template;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
        super.setSessionFactory(factory.unwrap(SessionFactory.class));
        this.template = this.getHibernateTemplate();
    }

    public HibernateTemplate getTemplate() {
        return template;
    }
}
