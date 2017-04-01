package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.TestDao;
import io.khasang.moika.entity.Test;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TestDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TestDaoImpl() {
    }

    @Override
    public void addTest(Test test) {
        sessionFactory.getCurrentSession().save(test);
    }

    @Override
    public void updateTest(Test test) {
        sessionFactory.getCurrentSession().update(test);
    }

    @Override
    public void deleteTest(Test test) {
        sessionFactory.getCurrentSession().delete(test);
    }

    @Override
    public Test getTestByID(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Test.class);
        criteria.add(Restrictions.eq("id", id));
        return (Test) criteria.uniqueResult();
    }

    @Override
    public List<Test> getAllTests() {
        return sessionFactory.getCurrentSession().createQuery("from test").list();
    }

    @Override
    public List<Test> getTestsByName(String name1, String name2) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Test.class);
        criteria.add(Restrictions.like("name1", name1));
        criteria.add(Restrictions.like("name2", name2));
        return (List<Test>) criteria.list();
    }
}
