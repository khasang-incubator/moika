package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.CarTypeDao;
import io.khasang.moika.entity.CarType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("carTypeDao")
public class CarTypeDaoImpl extends AllTypeDaoImpl<CarType> implements CarTypeDao {

    public CarTypeDaoImpl() {
    }

}
