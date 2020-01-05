package com.example;

import com.example.dao.ValueDao;
import com.example.dao.ValueRepository;
import com.example.entity.Value;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * springboot整合hibernate失败（问题在于无法获取到hibernateTemplate）
 * 所以这里使用session进行测试的
 * hibernate的一对多，多对一，多对多的测试
 *
 * 另：hibernate是JPA的实现
 *
 * Created by DINGJUN on 2019.12.15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class Test1 {
    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ValueDao dao;

    /**
     * 保存，删除，更新，查询单个对象 （注意：有没有发出sql语句）
     */
    @Test
    public void test1() {
        Value value = new Value();
        value.setQuestion("123");
        valueRepository.save(value); // insert into kb_val (question, id) values (?, ?)
        System.out.println(value);

        Value one = valueRepository.findOne(value.getId());
        System.out.println(one);
        value.setQuestion("456"); // 方法结束之后并没有将发出sql更新数据中的question为456（不知道是不是没有配置事务的问题，这里是用的是jpa，待使用template测试效果）
        valueRepository.saveAndFlush(value); // update kb_val set question=? where id=?
    }

    @Test
    public void test2() {
        HibernateTemplate template = dao.getTemplate();
        Session session = template.getSessionFactory().openSession();
        session.setFlushMode(FlushMode.COMMIT);
        Value value = new Value();
        value.setQuestion("3");
        session.save(value);
        session.flush();
        value.setQuestion("4");
//        int i =1 / 0; // 事务没有生效
        session.flush();
    }

    public void test3() {
        test(new cb() {
            @Override
            public void execute(Session session) {
                
            }
        });
    }

    public void test(cb cb) {
        HibernateTemplate template = dao.getTemplate();
        Session session = template.getSessionFactory().openSession();
        session.setFlushMode(FlushMode.COMMIT);
        cb.execute(session);
        session.flush();
    }

    public interface cb {
        void execute(Session session);
    }


}
