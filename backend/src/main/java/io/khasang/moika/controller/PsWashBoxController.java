package io.khasang.moika.controller;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.PskvorWashBoxDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Контроллер для управления боксами автомоек
 *
 * @author Pauls
 */
@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/washBox")
public class PsWashBoxController {

    @Autowired
    PskvorWashBoxDaoService pskvorWashBoxDaoService;

    /**
     * Вывод информации о всех боксах
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWashBoxList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getAllWashBoxes();
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", washBoxList.size() + " rows affected");
        return washBoxList;
    }

    /**
     * Добавление бокса
     *
     * @param washBox
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addWashBox(@RequestBody WashBox washBox, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        pskvorWashBoxDaoService.addWashBox(washBox);
        List<WashBox> washBoxList = new ArrayList<>();
        washBoxList.add(washBox);
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", "ID: " + washBox.getId() + " added");
        return washBoxList;
    }

    /**
     * Обновление информации о боксе
     *
     * @param washBox
     * @return
     */
    @RequestMapping(value = "/washBox/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateWashBox(@RequestBody WashBox washBox) {
        pskvorWashBoxDaoService.updateWashBox(washBox);
        return washBox;
    }

    /**
     * Выаод информаии о боксе по id
     *
     * @param inputId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WashBox getWashBox(@PathVariable(value = "id") String inputId, Model model) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBoxByID(Integer.valueOf(inputId));
        return washBox;
    }

    /**
     * Вывод информации о боксе по имени на конкретной мойке
     *
     * @param idFclt   - id мойки
     * @param boxName  - имя бокса
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/{idFacility}/{boxName}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WashBox getWashBoxesOnFacility(@PathVariable(value = "idFacility") String idFclt, @PathVariable(value = "boxName") String boxName,
                                         HttpServletResponse response, Model model) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBox(Integer.valueOf(idFclt), boxName);
        return washBox;
    }

    /**
     * Удаление бокса по id
     *
     * @param inputId
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteWashBox(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBoxByID(Integer.valueOf(inputId));
        if (washBox != null) {
            int id = washBox.getId();
            pskvorWashBoxDaoService.deleteWashBox(washBox);
            return String.valueOf(response.SC_OK);
        } else {return String.valueOf(response.SC_NOT_FOUND);}
    }

    /**
     * вывод списка типов боксов
     *
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping(value = "/ByType/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  List<WashBox> getWashBoxListbyType(@PathVariable(value = "type") String typeId, Model model) {
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getWashBoxesByType(Integer.valueOf(typeId));
        return washBoxList;
    }

    /**
     * вывод списка боксов по их статусам
     *
     * @param status
     * @param model
     * @return
     */
    @RequestMapping(value = "/ByStatus/{status}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<WashBox> getWashBoxListbyStatus(@PathVariable(value = "status") String status, Model model) {
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getWashBoxesByStatus(Integer.valueOf(status));
        return washBoxList;
    }

    /**
     * выод статутсов боксов
     *
     * @param boxStatus
     * @param model
     * @return
     */
    @RequestMapping(value = "/boxStatus/list/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxStatus> getBoxStatusList(@RequestBody BoxStatus boxStatus, Model model) {
        return pskvorWashBoxDaoService.getWashBoxesStatuses();//"ps-dao-carwashfacilities";
    }

    /**
     * Список типов боксов
     *
     * @param boxtypel
     * @return
     */
    @RequestMapping(value = "/boxType/list/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxType> getBoxTypesList(@RequestBody BoxType boxtypel) {
        return pskvorWashBoxDaoService.getWashBoxesTypes();//"ps-dao-carwashfacilities";
    }

    /**
     * статус бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxStatus/{code}/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus getBoxStatusByCode(@PathVariable(value = "code") String code) {
        return pskvorWashBoxDaoService.getWashBoxesStatusByCode(code);//"ps-dao-carwashfacilities";
    }

    /**
     * тип бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxType/{code}/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType getBoxTypesList(@PathVariable(value = "code") String code) {
        return pskvorWashBoxDaoService.getWashBoxesTypeByCode(code);//"ps-dao-carwashfacilities";
    }


}
