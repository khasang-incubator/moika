package io.khasang.moika.controller;

import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.service.MoikaServiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллек для стутусов моечных услуг
 *
 * @author Pauls
 */
@Controller
@RequestMapping(value = "/api/service/status")
public class PsServiceStatusController {
    @Autowired
    MoikaServiceStatusService serviceStatusService;


    /**
     * Список всех статусов услуг мойки
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getServiceStatusList() {
        List<ServiceStatus> serviceStatusList = serviceStatusService.getAllStatuses();
        if (serviceStatusList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return serviceStatusList;
    }

    /**
     * Добавление статуса
     *
     * @param newServiceStatus
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addServiceStatus(@RequestBody ServiceStatus newServiceStatus) {
        ServiceStatus serviceStatus = (ServiceStatus) serviceStatusService.addStatus(newServiceStatus);
        if (serviceStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceStatus;
    }

    /**
     * обновление информации  статуса
     *
     * @param updatedServiceStatus
     * @return
     */

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateServiceStatus(@RequestBody ServiceStatus updatedServiceStatus) {
        ServiceStatus serviceStatus = (ServiceStatus) serviceStatusService.updateStatus(updatedServiceStatus);
        if (serviceStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceStatus;
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
    public Object getServiceStatus(@PathVariable(value = "id") int id) {
        ServiceStatus serviceStatus = (ServiceStatus) serviceStatusService.getStatusById(id);
        if (serviceStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceStatus;
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
    public Object deleteServiceStatus(@PathVariable(value = "id") int deletedId) {
        ServiceStatus serviceStatus = (ServiceStatus) serviceStatusService.getStatusById(deletedId);
        if (serviceStatus != null) {
            int id = serviceStatus.getId();
            serviceStatusService.deleteStatus(serviceStatus);
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
    public Object getServiceStatusCode(@PathVariable(value = "code") String code) {
        ServiceStatus serviceStatus = (ServiceStatus) serviceStatusService.getStatusByCode(code);
        if (serviceStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return serviceStatus;
    }

}
