package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CatsDAO;
import io.khasang.moika.entity.Cats;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CatsDAOImpl implements CatsDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CatsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCat(Cats cat) {
        sessionFactory.getCurrentSession().save(cat);
    }

    @Override
    public void updateCat(Cats cat) {
        sessionFactory.getCurrentSession().update(cat);
    }

    @Override
    public List<Cats> getAllCats() {
        return sessionFactory.getCurrentSession().createQuery("from Cats").list();
    }

    @Override
    public Cats getCatById(long id) {
        return sessionFactory.getCurrentSession().get(Cats.class,id);
    }

    @Override
    public void deleteCatById(Cats cat) {
        sessionFactory.getCurrentSession().delete(cat);
    }

    @Override
    public boolean containCatById(long id) {
        Long countClients = sessionFactory.getCurrentSession().
                createQuery("select COUNT(c.id) from Cats c where c.id=:id", Long.class).
                setParameter("id", id).
                getSingleResult();
        return !countClients.equals(0);
    }
}
