package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("carDataAccessService")
@Transactional
public class CarDataAccessServiceImpl implements CarDataAccessService {
    @Autowired
    private CarDao carDao;


    public CarDataAccessServiceImpl() {
    }

    @Override
    public Car addCar(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car getCarById(long id) {
        return carDao.get(id);
    }

    @Override
    public List<Car> getCarByType(String type) {
        return carDao.getByTypeCode(type);
    }

    @Override
    public Car getCarByNumber(String number) {
        return carDao.getByNumber(number);
    }

    @Override
    public List<Car> getCarByModel(String model) {
        return carDao.getByModel(model);
    }

    @Override
    public List<Car> getCarList() {
        return carDao.getAll();
    }

    @Override
    public void deleteCar(long id) {
        Car car = carDao.get(id);
        if (car != null)  carDao.delete(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carDao.update(car);
    }

    @Override
    public List<Car> getByClient(long idClient) {
        return carDao.getByClient(idClient);
    }
}
