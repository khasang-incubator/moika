package io.khasang.moika.controller;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.CarType;
import io.khasang.moika.service.CarDataAccessService;
import io.khasang.moika.service.CarTypesDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private CarDataAccessService carService;

    @Autowired
    private CarTypesDataAccessService carTypeService;

    /**
     * Добавления автомобиля
     * @param newCar автомобиль для добавления
     * @return сохранённый автомобиль
     */

    @RequestMapping(value = "/add",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addCar(@RequestBody Car newCar){
        Car car = carService.addCar(newCar);
        if (car == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return car;
    }

    /**
     * Обновление автомобиля
     * @param car автомобиль для добавления
     * @return сохранённый автомобиль
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateCar(@ModelAttribute(value = "company") Car car){
        Car retCar = carService.updateCar(car);
        if (retCar == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return retCar;
    }

    /**
     * Удаления автомобиля
     * @param id автомобиля для удаления
     * @return redirect
     */
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteCar(@PathVariable("id") String id){
        Car car = carService.getCarById(Long.parseLong(id));
        if (car != null) {
            long idCar = car.getId();
            carService.deleteCar(idCar);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Возвращение автомобиля по id
     * @param id автомобиль для добавления
     * @return  автомобиль по id
     */
    @RequestMapping(value = "/byId/{id}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarById(@PathVariable("id") String id){
        Car car =carService.getCarById(Long.parseLong(id));
        if (car == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return car;
    }
    /**
     * Возвращение автомобиля по номеру
     * @param carNumber номер автомобиля
     * @return  автомобили
     */

    @RequestMapping(value = "/byNumber/{number}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarByNumber(@PathVariable("number") String carNumber){
        Car car = carService.getCarByNumber(carNumber);
        if (car == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return car;
    }
    /**
     * Возвращение автомобиля по модели
     * @param 'модель автмобиля
     * @return  автомобили
     */

    @RequestMapping(value = "/byModel/{model}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarsByModel(@PathVariable("model") String carModel){
        List<Car>  cars =  carService.getCarByModel(carModel);
        if (cars == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return cars;
    }
    /**
     * Возвращение cgbcrf автомобилtq по типу кузова
     * @param carType тип кузова
     * @return  автомобили
     */
    @RequestMapping(value = "/byType/{code}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarsByType(@PathVariable("code") String carType) {
        List<Car>  cars =  carService.getCarByType(carType);;
        if (cars == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return cars;
     }
    /**
     * Получение списка списка машин по коду
     * @return
     */

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object  getAllCars(){
        List<Car>  cars =  carService.getCarList();
        if (cars == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return cars;
    }

    /**
     * Получение списка списка машин по коду
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object  getCarsList(){
        List<Car>  cars =  carService.getCarList();
        if (cars == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return cars;
    }
    /**
     * Получение списка типа машин по коду
     * @param code
     * @return
     */
    @RequestMapping(value = "/type/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarTypes(@PathVariable(value = "code") String code) {
        CarType  carType =  (CarType) carTypeService.getTypeByCode(code);
        if (carType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return carType;
    }

    /**
     * Получение списка типов машин
     * @return
     */
    @RequestMapping(value = "/type/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarTypesList() {
        List<CarType> carTypeList = carTypeService.getAllTypes();
        if (carTypeList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return carTypeList;
    }

    /**
     * тип машин по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/type/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCarTypeById(@PathVariable(value = "id") int id) {
        CarType carType = (CarType) carTypeService.getTypeById(id);
        if (carType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return carType;
    }

    /**
     * Добавление нового типа машин
     *
     * @return
     */
    @RequestMapping(value = "/type/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addNewBoxType(@RequestBody CarType newCarType) {
        CarType carType = (CarType) carTypeService.addType(newCarType);
        if (carType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return carType;
    }

    /**
     * Обновление нового типа машин
     *
     * @return
     */
    @RequestMapping(value = "/type/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateBoxType(@RequestBody CarType newCarType) {
        CarType carType =  (CarType) carTypeService.updateType(newCarType);
        if (carType == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return carType;
    }

    /**
     * Удаление типа машин
     *
     * @return -  HTTPResponce status
     */
    @RequestMapping(value = "/type/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteBoxType(@PathVariable(value = "id") String inputId) {
        CarType carType = (CarType) carTypeService.getTypeById(Integer.valueOf(inputId));
        if (carType != null) {
            int id = carType.getId();
            carTypeService.deleteType(carType);
            return  new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
