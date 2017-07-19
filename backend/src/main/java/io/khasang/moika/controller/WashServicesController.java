package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pauls
 */

@RestController
@RequestMapping(value = "/api/washService/",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class WashServicesController {

    @Autowired
    WashServiceDataAccessService washServiceDAS;

    /**
     * Запрос всех услуг автомойки
     *
     * @return - list of services or error
     */
    @RequestMapping(value = "/washServiceDASList/{idFclt}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getBaseServiceList(@PathVariable(value = "idFclt") int idFclt) {
        List<WashService> serviceList = washServiceDAS.getServices(idFclt);
        if (serviceList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return serviceList;
    }

    /**
     * Добавление новой услуги
     *
     * @param washService
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashService(@RequestBody WashService washService) {
        washService = washServiceDAS.addService(washService);
        if (washService == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washService;
    }

    /**
     * Обновление услуги
     *
     * @param washService
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWashService(@RequestBody WashService washService) {
        washService = washServiceDAS.addService(washService);
        if (washService == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washService;
    }

    /**
     * Удаление услуги
     *
     * @param id - услуги
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashService(@PathVariable(value = "id") int id) {
        WashService washService = washServiceDAS.getServiceById(id);
        if (washService != null) {
            washServiceDAS.deleteService(washService);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Услуги по видам помывки
     */
    @RequestMapping(value = "/byGroup/{idFclt}/{group}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashServiceListByServiceGroup(@PathVariable(value = "idFclt") int idFclt, @PathVariable(value = "group") String group) {
        List<WashService> servicesList = washServiceDAS.getWashServiceByServiceGroup(idFclt, group);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    /**
     * Услуги по типу авто
     */
    @RequestMapping(value = "/byCarType/{idFclt}/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashServiceListByCarType(@PathVariable(value = "idFclt") int idFclt, @PathVariable(value = "type") String carType) {
        List<WashService> servicesList = washServiceDAS.getServicesByCarType(idFclt, carType);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    /**
     * Услуги конкретного вида услуг с ценой и длительностью подробностями в виде строки
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/byStatus/{idFclt}/{status}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashServiceListByStatus(@PathVariable(value = "idFclt") int idFclt, @PathVariable(value = "status") String status) {

        List<WashService> servicesList = washServiceDAS.getervicesByStatus(idFclt,status);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    /**
     * Действующите услуги помывки
     * @param idFclt
     * @return
     */

    @RequestMapping(value = "/actual/{idFclt}", method = RequestMethod.GET)
    public Object getActualWashServiceList(@PathVariable(value = "idFclt") int idFclt) {

        List<WashService> servicesList = washServiceDAS.getActualServices(idFclt);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }


}
