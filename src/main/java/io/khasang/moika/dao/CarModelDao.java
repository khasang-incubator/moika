package io.khasang.moika.dao;

import io.khasang.moika.entity.CarModel;

import java.util.List;

/**
 *
 */
public interface CarModelDao {
    CarModel addCarModel(CarModel carModel);
    CarModel updateCarModel(CarModel carModel);
    List<CarModel> getAllCarModel();
    CarModel getCarModelById(long id);
    void deleteCarModelById(long id);
    boolean containCarModelById(long id);
}

