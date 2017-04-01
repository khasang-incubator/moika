package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("carDao")
@Transactional
public class CarDaoImpl extends MoikaDaoCrudImpl<Car> implements CarDao {
    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);

    @Override
    public List<Car> getByType(String type) {
        return null;
    }

    @Override
    public List<Car> getByNumber(String number) {
        return null;
    }

    @Override
    public List<Car> getByModel(String model) {
        return null;
    }
}
