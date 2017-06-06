package io.khasang.moika.service.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.model.AKovalevDataAccess;
import io.khasang.moika.service.AKovalevDataAccessService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//@Service
@PropertySource(value = {"classpath:util.properties"})
public class AKovalevDataAccessServiceImpl implements AKovalevDataAccessService {
    @Autowired
    private AKovalevDataAccess akovalevDataAccess;
    @Autowired
    private Environment environment;

    @Override
    public List<Pair<String, Integer>> getCurrentDiscountForCars(Map<String, Integer> params) {
        return akovalevDataAccess.withCaseQuery();
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = akovalevDataAccess.selectAllCarsQuery();
        return cars.size() > 0 ? cars : null;
    }

    @Override
    public Car getCarById(long id) {
        if (akovalevDataAccess.containsCarQuery(id)) {
            return akovalevDataAccess.selectQuery(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Car> getCarsByType(String carType) {
        List<Car> cars = akovalevDataAccess.selectQuery(carType);
        return cars.size() > 0 ? cars : null;
    }

    @Override
    public boolean updateCar(Car car) {
        if (akovalevDataAccess.containsCarQuery(car.getId())) {
            akovalevDataAccess.updateQuery(car);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addCar(Car car) {
        akovalevDataAccess.insertQuery(car);
    }

    @Override
    public void adAllCars(List<Car> cars) {

    }

    @Override
    public List<Pair<Car, Client>> joinQuery() {
        return null;
    }

    @Override
    public List<Car> withinSelectQuery() {
        return null;
    }

    @Override
    public boolean deleteCarById(Long id) {
        if (akovalevDataAccess.containsCarQuery(id)) {
            akovalevDataAccess.deleteQuery(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCarByCarNumber(String carnumber) {
        akovalevDataAccess.deleteQuery(carnumber);
    }

    @Override
    public void flushCarTable() {
        akovalevDataAccess.truncateQuery();
    }

    @Override
    public void doBackupCarsTable() {
        akovalevDataAccess.doBackup(environment);
    }
}
