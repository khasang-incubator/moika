package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Контроллер для типов услу моесного сервиса
 * @author Pauls
 */
@Controller
@RequestMapping(value = "/api/service/type")
public class PsServiceTypeController {
    @Autowired
    MoikaServiceTypesService serviceTypeService;

    /**
     * Список типов услуг
     *
     * @return
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceStatusList() {
        List<ServiceType> serviceTypeList = serviceTypeService.getAllTypes();
        if (serviceTypeList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return serviceTypeList;
    }

    /**
     * Добавление статуса
     *
     * @param newServiceType
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addServiceType(@RequestBody ServiceType newServiceType) {
        ServiceType serviceType = (ServiceType) serviceTypeService.addType(newServiceType);
        if (serviceType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceType;
    }

    /**
     * обновление информации  статуса
     *
     * @param updatedServiceType
     * @return
     */

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateServiceType(@RequestBody ServiceType updatedServiceType) {
        ServiceType serviceType = (ServiceType) serviceTypeService.updateType(updatedServiceType);
        if (serviceType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceType;
    }

    /**
     * Статус по ID
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceType(@PathVariable(value = "id") int id) {
        ServiceType serviceType = (ServiceType) serviceTypeService.getTypeById(id);
        if (serviceType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceType;
    }

    /**
     * Elfktybtс по ID
     *
     * @param deletedId - id entity для удаления
     * @return
     */


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteServiceType(@PathVariable(value = "id") int deletedId) {
        ServiceType serviceType = (ServiceType) serviceTypeService.getTypeById(deletedId);
        if (serviceType != null) {
            int id = serviceType.getId();
            serviceTypeService.deleteType(serviceType);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Статус по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/byCode/{code}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceTypeByCode(@PathVariable(value = "code") String code) {
        ServiceType serviceType = (ServiceType) serviceTypeService.getTypeByCode(code);
        if (serviceType == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceType;
    }

}
