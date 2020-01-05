package com.example.generate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by DINGJUN on 2020.01.05.
 */
public class UUID implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }
}
