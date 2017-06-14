package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Controller
@RequestMapping(value =  "/MoikaService/service",
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
     * @param model
     * @return
     */
    @RequestMapping(value = "/allServiceList", method = RequestMethod.GET)
    public String getBaseServiceList(Model model) {
        List<MoikaService> serviceList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceList = allService.getAllServices();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("servicelist", serviceList);
        model.addAttribute("nrows", serviceList.size() + " rows affected");
        return "ps-dao-services";
    }

    /**
     * Добавление новой услуги
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST )
    @ResponseBody
    public MoikaService addMoikaService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    /**
     * Обновление услуги
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST )
    @ResponseBody
    public MoikaService updateWashService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    /**
     * Удаление услуги
     * @param id - услуги
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST )
    @ResponseBody
    public MoikaService deleteWashService(@PathVariable(value = "id") int id) {
        MoikaService moikaService = allService.getServiceById(id);
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    /**
     *  Услуги конкретного вида услуг c подробностями в
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceList/{code}", method = RequestMethod.GET )
    @ResponseBody
    public Object getWashServiceList(@PathVariable(value = "code") String typeCode, Model model) {
        List<MoikaService> servicesList = new ArrayList<>();
        try {
            servicesList = allService.getServicesByType(typeCode);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }


    /**
     * Услуги конкретного вида услуг с ценой и длительностью подробностями в виде строки
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceListByStatus/{status}", method = RequestMethod.GET )
    @ResponseBody
    public Object getWashServiceListByStatus(@PathVariable(value = "status") String status, Model model) {
        List<MoikaService> servicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            servicesList = allService.getAllervicesByStatus(status);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }

    /**
     * Все действующие услуги
     * @param model
     * @return
     */
    @RequestMapping(value = "/actualServiceList", method = RequestMethod.GET )
    @ResponseBody
    public Object getActualServiceListByStatus( Model model) {
        List<MoikaService> servicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            servicesList = allService.getActualServices();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }

    /**
     * Список типов услуг
     * @param model
     * @return
     */
    @RequestMapping(value = "/ServiceTypesList", method = RequestMethod.GET)
    public String getServiceTypeList(Model model) { //List<MoikaAllService>
        List<ServiceType> allServicesTypes = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            allServicesTypes = moikaServiceTypes.getAllTypes();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("serviceTypesList", allServicesTypes);
        model.addAttribute("nrows", allServicesTypes.size() + " rows affected");
        return "ps-dao-service-types";
    }

    /**
     * Список статусов услуг
     * @param model
     * @return
     */
    @RequestMapping(value = "/ServiceStatusList", method = RequestMethod.GET)
    public String getServiceStatusList(Model model) { //List<MoikaAllService>
        List<ServiceStatus> serviceStatus = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceStatus = moikaServiceStatus.getAllStatuses();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("serviceStatusList", serviceStatus);
        model.addAttribute("nrows", serviceStatus.size() + " rows affected");
        return "ps-dao-service-status";
    }

}
