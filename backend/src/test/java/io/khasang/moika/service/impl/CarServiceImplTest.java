package io.khasang.moika.service.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.CarType;
import io.khasang.moika.service.CarService;
import io.khasang.moika.service.CompanyService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class CarServiceImplTest {

    @Autowired
    CarService carService;

    Long newCarId;
    Car newCar;

    /**
     * Add car, take it from session, and check it's carType
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void addCar() throws Exception {
        Car car = new Car();
        String carType = "testCarType " + new Date().toString();
        car.setCarTypeEntity(new CarType("TESTCODE", carType));
        carService.addCar(car);

        Car newCar = carService.getCarById(car.getId());
        Assert.assertEquals(newCar.getCarTypeEntity().getTypeName(), carType);
    }

    /**
     * Get last added car by it's id
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void getCarById() throws Exception {
        Assert.assertTrue(newCarId != null);
        newCar = carService.getCarById(newCarId);
        Assert.assertNotNull(newCar);
    }

    @Test
    @Transactional
    @Rollback
    public void getCarList() throws Exception {
        List<Car> carList = carService.getCarList();

        Assert.assertTrue(!carList.isEmpty());

        Assert.assertTrue(!carList.contains(newCar));
    }

    @Test
    @Transactional
    @Rollback
    public void updateCar() throws Exception {
        String newDescription = "NewDescription "+new Date().toString();
        carService.updateCar(newCar);
        Assert.assertEquals(newDescription, newCar.getDescription());
    }

}