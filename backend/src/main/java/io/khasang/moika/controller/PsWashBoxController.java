package io.khasang.moika.controller;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.BoxStatusDataAccessService;
import io.khasang.moika.service.BoxTypesDataAccessService;
import io.khasang.moika.service.PskvorWashBoxDataAccessService;
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
@RequestMapping(value = "/api/washBox")
public class PsWashBoxController {

    @Autowired
    PskvorWashBoxDataAccessService pskvorWashBoxDataAccessService;

    @Autowired
    BoxStatusDataAccessService boxStatusDataAccessService;

    @Autowired
    BoxTypesDataAccessService boxTypesDataAccessService;

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
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getAllWashBoxes();
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", washBoxList.size() + " rows affected");
        return washBoxList;
    }

    /**
     * Добавление бокса
     *
     * @param washBox
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addWashBox(@RequestBody WashBox washBox) {
        pskvorWashBoxDataAccessService.addWashBox(washBox);
        List<WashBox> washBoxList = new ArrayList<>();
        washBoxList.add(washBox);
        return washBoxList;
    }

    /**
     * Обновление информации о боксе
     *
     * @param washBox
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateWashBox(@RequestBody WashBox washBox) {
        pskvorWashBoxDataAccessService.updateWashBox(washBox);
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
        WashBox washBox = pskvorWashBoxDataAccessService.getWashBoxById(Integer.valueOf(inputId));
        return washBox;
    }

    /**
     * Вывод информации о боксе по имени на конкретной мойке
     *
     * @param idFclt   - id мойки
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/inFacility/{idFacility}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<WashBox> getWashBoxesOnFacility(@PathVariable(value = "idFacility") int idFclt,
                                          HttpServletResponse response, Model model) {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesOnFacility(idFclt);
        return washBoxList;
    }

    /**
     * Удаление бокса по id
     *
     * @param inputId
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteWashBox(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        WashBox washBox = pskvorWashBoxDataAccessService.getWashBoxById(Integer.valueOf(inputId));
        if (washBox != null) {
            int id = washBox.getId();
            pskvorWashBoxDataAccessService.deleteWashBox(washBox);
            return String.valueOf(response.SC_OK);
        } else {
            return String.valueOf(response.SC_NOT_FOUND);
        }
    }

    /**
     * вывод списка  боксов по их типам
     *
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping(value = "/ByType/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<WashBox> getWashBoxListbyType(@PathVariable(value = "type") String typeId, Model model) {
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesByType(Integer.valueOf(typeId));
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
        List<WashBox> washBoxList = pskvorWashBoxDataAccessService.getWashBoxesByStatus(Integer.valueOf(status));
        return washBoxList;
    }

    /**
     * выод статутсов боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxStatus> getBoxStatusList() {
        return boxStatusDataAccessService.getAllStatuses();//"ps-dao-carwashfacilities";
    }

    /**
     * Список типов боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxType> getBoxTypesList() {
        return boxTypesDataAccessService.getAllTypes();//"ps-dao-carwashfacilities";
    }

    /**
     * статус бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxStatus/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus getBoxStatusByCode(@PathVariable(value = "code") String code) {
        return (BoxStatus) boxStatusDataAccessService.getStatusByCode(code);//"ps-dao-carwashfacilities";
    }

    /**
     * тип бокса по коду
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/boxType/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType getBoxTypeByCode(@PathVariable(value = "code") String code) {
        return (BoxType) boxTypesDataAccessService.getTypeByCode(code);
    }

    /**
     * статус бокса по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/boxStatus/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus getBoxStatusById(@PathVariable(value = "id") int id) {
        return (BoxStatus) boxStatusDataAccessService.getStatusById(id);//"ps-dao-carwashfacilities";
    }

    /**
     * тип бокса по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/boxType/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType getBoxTypeById(@PathVariable(value = "id") int id) {
        return (BoxType) boxTypesDataAccessService.getTypeById(id);
    }

    /**
     * Добавление нового статуса боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus addNewBoxStatus(@RequestBody BoxStatus newBoxStatus) {
        return (BoxStatus) boxStatusDataAccessService.addStatus(newBoxStatus);
    }

    /**
     * Добавление нового типа боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType addNewBoxType(@RequestBody BoxType newBoxType) {
        return (BoxType) boxTypesDataAccessService.addType(newBoxType);
    }

    /**
     * Обновление нового статуса боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxStatus/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus updateBoxStatus(@RequestBody BoxStatus newBoxStatus) {
        return (BoxStatus) boxStatusDataAccessService.updateStatus(newBoxStatus);
    }

    /**
     * Обновление нового типа боксов
     *
     * @return
     */
    @RequestMapping(value = "/boxType/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType updateBoxType(@RequestBody BoxType newBoxType) {
        return (BoxType) boxTypesDataAccessService.updateType(newBoxType);
    }

    /**
     * Удаление статуса боксов
     *
     * @return - HTTPResponce status
     */
    @RequestMapping(value = "/boxStatus/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteBoxStatus(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        BoxStatus boxStatus = (BoxStatus)boxStatusDataAccessService.getStatusById(Integer.valueOf(inputId));
        if (boxStatus != null) {
            int id = boxStatus.getId();
            boxStatusDataAccessService.deleteStatus(boxStatus);
            return String.valueOf(response.SC_OK);
        } else {
            return String.valueOf(response.SC_NOT_FOUND);
        }
    }

    /**
     * Удаление типа боксов
     *
     * @return  -  HTTPResponce status
     */
    @RequestMapping(value = "/boxType/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteBoxType(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        BoxType boxType = (BoxType)boxTypesDataAccessService.getTypeById(Integer.valueOf(inputId));
        if (boxType != null) {
            int id = boxType.getId();
            boxTypesDataAccessService.deleteType(boxType);
            return String.valueOf(response.SC_OK);
        } else {
            return String.valueOf(response.SC_NOT_FOUND);
        }
    }
}
