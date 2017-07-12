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
@RequestMapping(value = "/api/moikaService/",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class PsServicesController {
    @Qualifier("moikaServiceDataAccessService")
    @Autowired
    MoikaServiceDataAccessService allService;
    @Qualifier("washServiceDataAccessService")
    @Autowired
    WashServiceDataAccessService washService;
    @Qualifier("polishServiceDataAccessService")
    @Autowired
    PolishServiceDataAccessService polishService;
    @Qualifier("otherServiceDataAccessService")
    @Autowired
    OtherServiceDataAccessService otherService;
    @Qualifier("cleanServiceDataAccessService")
    @Autowired
    CleanServiceDataAccessService cleanService;
    @Qualifier("chemCleanServiceDataAccessService")
    @Autowired
    ChemCleanServiceDataAccessService chemCleanService;
    @Autowired
    MoikaServiceTypesService moikaServiceTypes;
    @Autowired
    MoikaServiceStatusService moikaServiceStatus;

    /**
     * Запрос всех услуг автомойки
     *
     * @return - list of services or error
     */
    @RequestMapping(value = "/allServiceList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getBaseServiceList() {
        List<MoikaService> serviceList = allService.getAllServices();
        if (serviceList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return serviceList;
    }

    /**
     * Добавление новой услуги
     *
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addMoikaService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        if (moikaService == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return moikaService;
    }

    /**
     * Обновление услуги
     *
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWashService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        if (moikaService == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return moikaService;
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
        MoikaService moikaService = allService.getServiceById(id);
        if (moikaService != null) {
            allService.deleteService(moikaService);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Услуги конкретного вида услуг c подробностями в
     *
     * @param typeCode
     * @return
     */
    @RequestMapping(value = "/byType/{code}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceListByType(@PathVariable(value = "code") String typeCode) {
        List<MoikaService> servicesList = allService.getServicesByType(typeCode);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    /**
     * Услуги по типу авто
     */
    @RequestMapping(value = "/byCarType/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceListByCarType(@PathVariable(value = "type") String carType) {
        List<MoikaService> servicesList = allService.getServicesByCarType(carType);
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
    @RequestMapping(value = "/byStatus/{status}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceListByStatus(@PathVariable(value = "status") String status) {

        List<MoikaService> servicesList = allService.getAllervicesByStatus(status);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }


    @RequestMapping(value = "/onFclt/{idFclt}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceListonFclt(@PathVariable(value = "idFclt") int idFclt) {

        List<MoikaService> servicesList = allService.getServicesOnFacility(idFclt);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    @RequestMapping(value = "/onFclt/actual/{idFclt}", method = RequestMethod.GET)
    public Object getActualServiceListByFclt(@PathVariable(value = "idFclt") int idFclt) {

        List<MoikaService> servicesList = allService.getServicesOnFacility(idFclt);
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }

    /**
     * Все действующие услуги
     *
     * @return
     */
    @RequestMapping(value = "/actual", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getActualServiceListByStatus() {
        List<MoikaService> servicesList = allService.getActualServices();
        if (servicesList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return servicesList;
    }


}
