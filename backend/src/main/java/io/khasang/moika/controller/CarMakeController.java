package io.khasang.moika.controller;


import io.khasang.moika.entity.CarMake;
import io.khasang.moika.service.CarMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер интерфейсов марки автомобиля
 *
 * @author Lyubarev Aleksandr
 * @since 2017-03-14
 */
@Controller
@RequestMapping("api/CarMake")
public class CarMakeController {
    @Autowired
    private CarMakeService carMakeService;

    /**
     * Добавления автомобиля
     *
     * @param carMake марка автомобиля для добавления
     * @return марка автомобиля
     */

    @RequestMapping(value = "/add/",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CarMake addCarMake(@RequestBody CarMake carMake) {
        carMakeService.addCarMake(carMake);
        return carMake;
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CarMake carMake(@PathVariable(value = "id") String id) {
        return carMakeService.getCarMakeById(Long.parseLong(id));
    }

    @RequestMapping(value = "/carMake/getAll/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CarMake> carMakeList() {

        return carMakeService.getCarMakeList();
    }


}
