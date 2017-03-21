package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import io.khasang.moika.service.MoikaServiceStatusService;
import io.khasang.moika.service.MoikaServiceTypesService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PsServicesController {
    @Autowired
    MoikaServiceDataAccessService allService;
    @Autowired
    WashServiceDataAccessService washService;
    @Autowired
    MoikaServiceTypesService moikaServiceTypes;
    @Autowired
    MoikaServiceStatusService moikaServiceStatus;


    @RequestMapping(value = "/MoikaService/allServiceList", method = RequestMethod.GET)
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

    @RequestMapping(value = "/MoikaService/service/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService addWashService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/MoikaService/service/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService updateWashService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/MoikaService/service/delete/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService deleteWashService(@RequestBody MoikaService moikaService) {
        moikaService = allService.addService(moikaService);
        return moikaService; //"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/MoikaService/washServiceList", method = RequestMethod.GET)
    public String getWashServiceList(Model model) {
        List<WashService> washServicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            washServicesList = washService.getAllConcreatServices();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("servicelist", washServicesList);
        model.addAttribute("nrows", washServicesList.size() + " rows affected");
        return "ps-dao-wash-services";
    }


    @RequestMapping(value = "/MoikaService/ServiceTypesList", method = RequestMethod.GET)
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


    @RequestMapping(value = "/MoikaService/ServiceStatusList", method = RequestMethod.GET)
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
