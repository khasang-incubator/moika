package io.khasang.moika.controller;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.CarType;
import io.khasang.moika.service.CarService;
import io.khasang.moika.service.CarTypesDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер интерфейсов автомобиля
 *
 * @author Nikolay Ilichev, Lyubarev Aleksandr
 * @since 2017-03-01
 */
@Controller
@RequestMapping(value = "/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CarTypesDataAccessService carTypeService;

    /**
     * Добавления автомобиля
     * @param car автомобиль для добавления
     * @return сохранённый автомобиль
     */

    @RequestMapping(value = "/add",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car addCar(@RequestBody Car car){
        carService.addCar(car);
        return car;
    }

    /**
     * Обновление автомобиля
     * @param car автомобиль для добавления
     * @return сохранённый автомобиль
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car updateCar(@ModelAttribute(value = "company") Car car, @PathVariable("id") String id){
        car = carService.updateCar(car);
        return car;
    }

    /**
     * Удаления автомобиля
     * @param id автомобиля для удаления
     * @return redirect
     */
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String deleteCar(@PathVariable("id") String id){
        carService.deleteCar(Long.parseLong(id));
        return "redirect:/car";
    }

    /**
     * Возвращение автомобиля по id
     * @param id автомобиль для добавления
     * @return  автомобиль по id
     */
    @RequestMapping(value = "id/{id}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car getCarById(@PathVariable("id") String id){
        return carService.getCarById(Long.parseLong(id));
    }
    /**
     * Возвращение автомобиля по номеру
     * @param carNumber номер автомобиля
     * @return  автомобили
     */

    @RequestMapping(value = "/number/{number}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Car> getCarByNumber(@PathVariable("number") String carNumber){
        return carService.getCarByNumber(carNumber);
    }
    /**
     * Возвращение автомобиля по модели
     * @param 'модель автмобиля
     * @return  автомобили
     */

    @RequestMapping(value = "/model/{model}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Car> getCarByModel(@PathVariable("model") String carModel){
        return carService.getCarByModel(carModel);
    }
    /**
     * Возвращение автомобиля по типу кузова
     * @param carType тип кузова
     * @return  автомобили
     */
    @RequestMapping(value = "/type/{type}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Car> getCarByType(@PathVariable("type") String carType) {
        return carService.getCarByType(carType);
     }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @ResponseBody
    public List<Car>  getCarList(){
       return carService.getCarList();
    }

    @RequestMapping(value = "/carType/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CarType getCarTypes(@PathVariable(value = "code") String code) {
        return (CarType)carTypeService.getTypeByCode(code);
    }

    @RequestMapping(value = "/carType/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<CarType> getCarTypesList() {
        return carTypeService.getAllTypes();
    }
}
