package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CityDao;
import io.khasang.moika.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("cityDao")
public class CityDaoImpl extends MoikaDaoCrudImpl<City> implements CityDao {

    public CityDaoImpl() {
    }


    public CityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public City getByName(String cityName){
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from cities  where name = ?");
        query.setParameter(0,cityName);
        return (City)query.getSingleResult();
    }
}
