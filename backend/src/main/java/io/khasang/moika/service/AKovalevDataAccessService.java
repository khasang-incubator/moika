package io.khasang.moika.service;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface AKovalevDataAccessService {
    List<Pair<String, Integer>> getCurrentDiscountForCars(Map<String, Integer> params);
    List<Car> getAllCars();
    Car getCarById(long id);
    List<Car> getCarsByType(String type);
    boolean updateCar(Car car);
    void addCar(Car car);
    void adAllCars(List<Car> cars);
    List<Pair<Car, Client>> joinQuery();
    List<Car> withinSelectQuery();
    boolean deleteCarById(Long id);
    void deleteCarByCarNumber(String carnumber);
    void flushCarTable();
    void doBackupCarsTable();
}
