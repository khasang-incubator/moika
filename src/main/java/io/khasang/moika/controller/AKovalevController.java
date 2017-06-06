package io.khasang.moika.controller;

import io.khasang.moika.entity.Car;
import io.khasang.moika.service.AKovalevDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
@RequestMapping("/akovalev")
public class AKovalevController {

    @Autowired
    AKovalevDataAccessService akovalevDataAccessService;

    @RequestMapping("/")
    public String getListCar(Model model) {
        model.addAttribute("listCars", akovalevDataAccessService.getAllCars());
        return "listcars";
    }

    @RequestMapping("/editcar")
    public String editCar(Model model, @RequestParam long id) {
        model.addAttribute("car", akovalevDataAccessService.getCarById(id));
        return "editcar";
    }

    @RequestMapping(value = "/savecar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveCar(Model model, @RequestBody Car car) {
        model.addAttribute("isSaveCar", akovalevDataAccessService.updateCar(car));
        model.addAttribute("car", car);
        return "editcar";
    }

    @RequestMapping("/addcar")
    public String addCar(Model model, @RequestBody Car car) {
        akovalevDataAccessService.addCar(car);
        return "listcars";
    }
}
