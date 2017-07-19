package io.khasang.moika.controller;

import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping(value ="api/work/",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class WorkController {
    @Autowired
    WorkAccessService workDAS;

    @RequestMapping(value = "list/{idFclt}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object listWork(@PathVariable(value = "idFclt") int idFclt) {
        List<Work> worksList = workDAS.getAllWorks(idFclt);
        if ((worksList == null) || (worksList.isEmpty()))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return worksList;
    }

    /**
     * добавление информации о заказе
     *
     * @param work
     * @return
     */
    @RequestMapping(value = "add/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashBox(@RequestBody Work work) {
        Work resWork = workDAS.createWork(work);
        if (resWork == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resWork;
    }

    /**
     * Обновление информации о заказе
     *
     * @param work
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWork(@RequestBody Work work) {
        Work resWork = workDAS.updateWork(work);
        if (resWork == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resWork;
    }

    /**
     * Выаод информаии о заказе по id
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public Object getWorkById(@PathVariable(value = "id") long workId) {
        Work resWork = workDAS.getWork(workId);
        if (resWork == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resWork;
    }

    /**
     * Удаление заказа по id
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashBox(@PathVariable(value = "id") long workId) {
        Work resWork = workDAS.getWork(workId);
        if (resWork != null) {
            long id = resWork.getIdWork();
            if (id != 0 ) {
                workDAS.deleteWork(resWork);
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
