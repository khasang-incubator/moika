package io.khasang.moika.service.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.CarType;
import io.khasang.moika.entity.Client;
import io.khasang.moika.service.CarDataAccessService;
import io.khasang.moika.service.CarTypesDataAccessService;
import org.junit.Assert;
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
    CarDataAccessService carService;
    @Autowired
    CarTypesDataAccessService carTypeService;

    String carTypeCode="SUV";
    String carTestNote = "Тестовый автомобиль";
    String carTestDescr = "Test descr"+new Date().toString();
    String carTestNum =  "M753CT77";
    String testCarModel = "FW";
    long testId = 3L;

    /**
     * Add car, take it from session, and check it's carType
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void addCar() throws Exception {
        Car car = new Car();
        car.setCarNumber(carTestNum);
        CarType carType = (CarType)carTypeService.getTypeByCode(carTypeCode);
        Assert.assertTrue("Setting Car type code not " + carTypeCode +", but "+carType.getTypeCode(), carType.getTypeCode().equalsIgnoreCase(carTypeCode));
        car.setCarTypeEntity(carType);
        car.setNote(carTestNote);
        car.setCarModel(testCarModel);
        carService.addCar(car);
        Car retCar = carService.getCarById(car.getId());

        Assert.assertNotNull("Return car is null", retCar);
        Assert.assertNotNull("Return car typeEntity is null", retCar.getCarTypeEntity());
        Assert.assertTrue("Car type code not " + carTypeCode+", but "+ retCar.getCarTypeEntity().getTypeCode(), retCar.getCarTypeEntity().getTypeCode().equalsIgnoreCase(carTypeCode));
        Assert.assertTrue("Car number  not " + carTestNum+", but "+retCar.getCarNumber(), retCar.getCarNumber().equalsIgnoreCase(carTestNum));
        Assert.assertTrue("Car note  not " + carTestNote+", but "+retCar.getNote(), retCar.getNote().equalsIgnoreCase(carTestNote));
    }

    /**
     * Get last added car by it's id
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback
    public void getCarById() throws Exception {
        Car retCar = carService.getCarById(testId);
        Assert.assertNotNull("Return car is null", retCar);
        Assert.assertEquals("Not the same id", retCar.getId(), testId);
    }

    @Test
    @Transactional
    @Rollback
    public void getCarList() throws Exception {
        List<Car> carList = carService.getCarList();
        Assert.assertNotNull("Return car list  is null", carList);
        Assert.assertTrue("Car list is empty", !carList.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void updateCar() throws Exception {
        Car car = carService.getCarById(testId);
        car.setDescription(carTestDescr);
        Car retCar = carService.updateCar(car);
        Assert.assertEquals("Not this car", retCar.getId(), car.getId());
        Assert.assertTrue("Not correct descr", retCar.getDescription().equalsIgnoreCase(carTestDescr));
    }

    @Test
    @Transactional
    @Rollback
    public void getCarsClient() throws Exception {
        Car car = carService.getCarById(testId);
        List<Client> clientList = car.getClients();
        Assert.assertNotNull("Car clients list is null", clientList );
        Assert.assertFalse("Car clients list is empty", clientList.isEmpty() );
    }

}