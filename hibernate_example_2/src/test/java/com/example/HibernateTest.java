package com.example;

import com.example.dao.ValueDao;
import com.example.entity.Faq;
import com.example.entity.Value;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DINGJUN on 2020.01.05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Autowired
    private ValueDao valueDao;

    private HibernateTemplate template;

    @Before
    public void before() {
        this.template = valueDao.getTemplate();
    }
    @Test
    @Transactional
    @Rollback(false) // spring单元测试默认不提交事务
    public void test1() {
        /**
         * 插入一条数据,并对这个对象进行修改,session关闭的时候,会再次发出sql语句更新数据
         */
        Value value = new Value();
        value.setQuestion("1");
        valueDao.getTemplate().save(value); // insert into kb_val (question, id) values (?, ?)
        value.setQuestion("2"); // update kb_val set question=? where id=?
    }
    @Test
    @Transactional
    @Rollback(false) // spring单元测试默认不提交事务
    public void test2() {
        /**
         * 通过HibernateTemplate的execute方法回调的session对象创建Hql查询
         * 查询出来的对象是持久态的对象，修改之后会在session关闭的时候同步到数据库中
         */
        Value value = template.execute(new HibernateCallback<Value>() {
            public Value doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("select v from Value v where v.question=?");
                query.setString(0, "2");
                List<Value> list = query.list(); // select value0_.id as id1_2_, value0_.question as question2_2_ from kb_val value0_ where value0_.question=?
                return list.get(0);
            }
        });
        System.out.println(value);
        value.setQuestion("3"); // update kb_val set question=? where id=?
    }
    @Test
    @Transactional
    @Rollback(false) // spring单元测试默认不提交事务
    public void test3() {
        /**
         * 一对多的级联保存
         */
        Value v = new Value();
        v.setQuestion("2");
        Faq faq = new Faq();
        faq.setQuestion("21");
        faq.setValue(v);
        HashSet<Faq> faqs = new HashSet<Faq>();
        faqs.add(faq);
        v.setFaq(faqs);
        template.save(v); // insert into kb_val (question, id) values (?, ?) // insert into kb_faq (question, v_id, id) values (?, ?, ?)
    }

    @Test
    @Transactional
    @Rollback(false) // spring单元测试默认不提交事务
    public void test4() {
        // select value0_.id as id1_2_, value0_.question as question2_2_ from kb_val value0_ where value0_.question=?
        List<Value> values = (List<Value>) template.find("select v from Value v where v.question=? ", "2");
        Value value = values.get(0);
        // Lazy加载方式会在get的时候再次发出语句查询
        // select faqs0_.v_id as v_id3_2_1_, faqs0_.id as id1_0_1_, faqs0_.id as id1_0_0_, faqs0_.question as question2_0_0_, faqs0_.v_id as v_id3_0_0_ from kb_faq faqs0_ where faqs0_.v_id=?
        Set<Faq> faqs = value.getFaqs();
        Faq faq = new Faq();
        faq.setQuestion("22");
        faq.setValue(value);
        faqs.add(faq);
        // insert into kb_faq (question, v_id, id) values (?, ?, ?)
        // 插入数据关联关系先绑定然后才能插入到数据库中
    }

    @Test
    @Transactional
    @Rollback(false) // spring单元测试默认不提交事务
    public void test5() {
//        Faq faq = new Faq();
//        faq.setId("3beeb6f802744fd3aae051ae48826d2c");
//        template.delete(faq); // delete from kb_faq where id=? // 删除多的一方，直接删除就可以，通过id删除 这是方式一，方式二，先查询再删除



        // 删除一的一方，方式一，通过new对象设置id方式删除无法使用级联，是托管状态的对象，需要通过方式二，先查询对象为持久态然后再删除
//        Value v = new Value();
//        v.setId("2bfbb127a4604d859b7de097affd3e89");
//        template.delete(v);
        List<Value> values = (List<Value>) template.find("select v from Value v where v.question=? ", "1");
        Value value = values.get(0);
        template.delete(value);
//        Hibernate: select value0_.id as id1_2_, value0_.question as question2_2_ from kb_val value0_ where value0_.question=?
//        Hibernate: select faqs0_.v_id as v_id3_2_1_, faqs0_.id as id1_0_1_, faqs0_.id as id1_0_0_, faqs0_.question as question2_0_0_, faqs0_.v_id as v_id3_0_0_ from kb_faq faqs0_ where faqs0_.v_id=?
//        Hibernate: delete from kb_faq where id=?
//        Hibernate: delete from kb_val where id=?

    }

}
