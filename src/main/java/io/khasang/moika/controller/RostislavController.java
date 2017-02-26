package io.khasang.moika.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import io.khasang.moika.service.RostislavDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/rostislav")
@Controller
public class RostislavController {

    private final
    RostislavDataAccessService rostislavDataAccessService;

    private final CarService carService;

    @Autowired
    public RostislavController(RostislavDataAccessService rostislavDataAccessService, CarService carService) {
        this.rostislavDataAccessService = rostislavDataAccessService;
        this.carService = carService;
    }

    @RequestMapping("/listAllCars")
    public String listAllCars(Model model) {
        model.addAttribute("name", "Хороший человек");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("information", "Вот все тачки, что у нас зарегистрированы: " + rostislavDataAccessService.getAllCars().toString());
        return "index";
    }

    @RequestMapping("/listCarsOfType")
    public String listCarsOfType(Model model) {
        model.addAttribute("name", "Хороший человек");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("information", "Вот все тачки типа LADA, что у нас зарегистрированы: "
                + rostislavDataAccessService.getCars("lada").toString());
        return "index";
    }

    /**
     * Принимает JSON-описание машины, создаёт с этими данными объект машины и сохраняет его в БД.
     * Возвращает JSON-описание созданной машины.
     *
     * @param car автомобиль для добавления
     * @return сохранённый автомобиль
     */
    @RequestMapping(value = "/car/addViaJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCarViaJson(@RequestBody Car car) {
        carService.addCar(car);
        return car;
    }

    /**
     * Принимает JSON-описание машины, создаёт с этими данными объект машины и сохраняет его в БД.
     * Возвращает JSON-описание созданной машины.
     *
     * @return сохранённый автомобиль
     */
    @RequestMapping(value = "/car/addViaPathVariable/description/{description}/carType/{carType}",
            method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCarVia(@PathVariable(value = "description") String description,
                            @PathVariable(value = "carType") String carType) {
        Car car = new Car();
        car.setDescription(description);
        car.setCarType(carType);
        carService.addCar(car);
        return car;
    }


    @RequestMapping(value = "/car/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Car> getCompanyList() {
        return carService.getCarList();
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateCompany(@RequestBody Map<String, Object> carValuesMap) {

        String carId = (String) carValuesMap.remove("id");

        return carService.updateCar(Long.parseLong(carId), carValuesMap);
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Car getCar(@RequestParam(value = "carId") String carId) {
        return carService.getCarById(Long.parseLong(carId));
    }


}
