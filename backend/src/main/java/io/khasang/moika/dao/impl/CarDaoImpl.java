package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("carDao")
@Transactional
public class CarDaoImpl extends MoikaDaoCrudImpl<Car> implements CarDao {
    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);

    @Override
    public List<Car> getByTypeCode(String typeCode) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from cars where carTypeEntity.code = :typeCode");
        query.setParameter("typeCode", typeCode);
        List<Car> cars = query.getResultList();
        return cars;
    }

    @Override
    public Car getByNumber(String number) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from cars where carNumber = :number");
        query.setParameter("number", number);
        Car car = (Car)query.getSingleResult();
        return car;
    }

    @Override
    public List<Car> getByModel(String model) {
        return null;
    }

}
