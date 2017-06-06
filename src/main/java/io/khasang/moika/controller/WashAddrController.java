package io.khasang.moika.controller;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.service.WashAddrDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pauls on 31.05.2017.
 */

@Controller
@RequestMapping(value = "/api/washAddr")
public class WashAddrController {

    @Autowired
    WashAddrDataAccessService washAddrdDAS;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashAddrList() {
        List<WashAddr> addrList = washAddrdDAS.getAllWashAddr();
        if (addrList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return addrList;
    }

    /**
     * Добавление нового адреса
     *
     * @param newWashAddr
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashAddr(@RequestBody WashAddr newWashAddr) {
        WashAddr washAddr = washAddrdDAS.addWashAddr(newWashAddr);
        if (washAddr == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washAddr;
    }

    /**
     * обновление информации об адресе
     *
     * @param updatedWashAddr
     * @return
     */

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateWashAddr(@RequestBody WashAddr updatedWashAddr) {
        WashAddr washAddr = (WashAddr) washAddrdDAS.updateWashAddr(updatedWashAddr);
        if (washAddr == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washAddr;
    }

    /**
     * адрес по ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashAddrById(@PathVariable(value = "id") int id) {
        WashAddr washAddr = (WashAddr) washAddrdDAS.getWashAddrById(id);
        if (washAddr == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washAddr;
    }


    /**
     * адрес по ID города
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/byCityId/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getWashAddrByCityId(@PathVariable(value = "id") int id) {
        List<WashAddr> washAddrList =  washAddrdDAS.getWashAddrListInCity(id);
        if (washAddrList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return washAddrList;
    }


    /**
     * Удапление по ID
     *
     * @param deletedId - id entity для удаления
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashAddr(@PathVariable(value = "id") int deletedId) {
        WashAddr washAddr = washAddrdDAS.getWashAddrById(deletedId);
        if (washAddr != null) {
            int id = washAddr.getId();
            washAddrdDAS.deleteWashAddr(id);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCityList() {
        List<City> cityList = washAddrdDAS.getCityList();
        if (cityList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return cityList;
    }

    /**
     * Добавление статуса
     *
     * @param newCity
     * @return
     */
    @RequestMapping(value = "/city/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addCity(@RequestBody City newCity) {
        City city = washAddrdDAS.addCity(newCity);
        if (city == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return city;
    }

    /**
     * обновление информации города
     *
     * @param updatedCity
     * @return
     */

    @RequestMapping(value = "/city/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updatCity(@RequestBody City updatedCity) {
        City city =  washAddrdDAS.updateCity(updatedCity);
        if (city == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return city;
    }

    /**
     * город по ID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/city/byId/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCityById(@PathVariable(value = "id") int id) {
        City city =  washAddrdDAS.getCityById(id);
        if (city == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return city;
    }

    /**
     * город по названию
     *
     * @param cityName
     * @return
     */
    @RequestMapping(value = "/city/byName/{name}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCityByName(@PathVariable(value = "name") String cityName) {
        City city =  washAddrdDAS.getCityByName(cityName);
        if (city == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return city;
    }

    /**
     * город по id Ареса
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/city/byAddId/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getCityByAddrId(@PathVariable(value = "id") int id) {
        City city =  washAddrdDAS.getCityByAddrId(id);
        if (city == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return city;
    }


    /**
     * Удапление города по ID
     *
     * @param deletedId - id entity для удаления
     * @return
     */
    @RequestMapping(value = "/city/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteCity(@PathVariable(value = "id") int deletedId) {
        City city = washAddrdDAS.getCityById(deletedId);
        if (city != null) {
            int id = city.getId();
            washAddrdDAS.deleteCity(id);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
