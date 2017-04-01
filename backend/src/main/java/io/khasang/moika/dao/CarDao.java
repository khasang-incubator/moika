package io.khasang.moika.dao;


import io.khasang.moika.entity.Car;

import java.util.List;

public interface CarDao extends IMoikaDaoCrud<Car>{
    List<Car> getByType(String type);
    List<Car> getByNumber(String number);
    List<Car> getByModel(String model);
}