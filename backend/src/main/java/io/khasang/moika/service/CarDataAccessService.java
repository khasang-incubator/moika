package io.khasang.moika.service;

import io.khasang.moika.entity.Car;

import java.util.List;

public interface CarDataAccessService {
    Car addCar(Car car);

    Car getCarById(long id);

    List getCarByType(String type);

    Car getCarByNumber(String number);

    List getCarByModel(String model);

    List<Car> getCarList();

    void deleteCar(long id);

    Car updateCar(Car car);

    List<Car> getByClient(long idClient);
}
