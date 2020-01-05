package com.example.data.source;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.persistence.EntityManagerFactory;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@Configuration
public class DataConfig {

    @Autowired
    private EntityManagerFactory factory;

    @Bean
    public SessionFactory sessionFactory () {

        return factory.unwrap(SessionFactory.class);
    }

}
