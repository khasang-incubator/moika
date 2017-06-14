package io.khasang.moika.controller;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import io.khasang.moika.service.WashAddrDataAccessService;
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
 * @author Pauls
 */

@RestController
@RequestMapping(value = "/api/washFacility",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class PsWashFacilityController {

    @Autowired
    PskvorWashFacilityDaoService pskvorWashFacilityDaoService;
    @Autowired
    WashAddrDataAccessService washAddrDAS;

    /**
     * Список всех моек
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashFacilityList() {
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getAllWashFacilities();
        if (washFacilityList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washFacilityList;
    }

    /**
     * Добавление новой мойки
     *
     * @param newWashFacility
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashFacility(@RequestBody WashFacility newWashFacility) {
        WashFacility washFacility = pskvorWashFacilityDaoService.addWashFacility(newWashFacility);
        if (washFacility == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washFacility;
    }

    /**
     * Обновление информации о мойке
     *
     * @param updatedWashFacility
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWashFacility(@RequestBody WashFacility updatedWashFacility) {
        WashFacility washFacility = pskvorWashFacilityDaoService.updateWashFacility(updatedWashFacility);
        if (washFacility == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washFacility;
    }

    /**
     * вывод мойки по id
     *
     * @param idFclt
     * @return
     */
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashFacility(@PathVariable(value = "id") int idFclt) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(idFclt);
        if (washFacility == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washFacility;
    }

    /**
     * Вывод списка моек в моечной сети
     *
     * @param idNet - id Сети
     * @return
     */
    @RequestMapping(value = "/OnNet/{idNet}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getWashFacilityesOnNet(@PathVariable(value = "idNet") int idNet) {
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getWashFacilitiesOnNet(idNet);
        if (washFacilityList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return washFacilityList;
    }

    /**
     * Удалени мойки по id
     *
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashFacility(@PathVariable(value = "id") int inputId) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(Integer.valueOf(inputId));
        if (washFacility != null) {
            int id = washFacility.getId();
            pskvorWashFacilityDaoService.deleteWashFacility(washFacility);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Вывод списка моек в городе
     *
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/inCity/{cityId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getFacilitiesInCity(@PathVariable(value = "cityId") int cityId) {
        City city = washAddrDAS.getCityById(cityId);
        if (city != null) {
            List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getWashFacilitiesInCity(city);
            if (washFacilityList == null)
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            else
                return washFacilityList;
        }
        else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }


    /**
     * Вывод списка моек в городе
     *
     * @param addrId
     * @return
     */
    @RequestMapping(value = "/onAddr/{addrId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object getFacilityByAddr(@PathVariable(value = "addrId") int addrId) {
        WashAddr addr = washAddrDAS.getWashAddrById(addrId);
        if (addr != null) {
            WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByAddres(addr);
            if (washFacility == null)
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            else
                return washFacility;
        }
        else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    /**
     * Вывод мойки по координатам
     *
     * @param coord
     * @return
     */
  /*  @RequestMapping(value = "/byCoord/{coord}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getFacilityByAddr(@PathVariable(value = "coord") Coordinate coord, Model model) {
        //   model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        //   List<WashBox> washBoxesList = pskvorWashFacilityDaoService.getWashBoxesOnFacility(Integer.valueOf(idFclt));
        //   model.addAttribute("boxlist", washBoxesList);
        //  model.addAttribute("nrows", washBoxesList.size() + " rows affected");
        return "ps-dao-carwashbox";
    } */
}
