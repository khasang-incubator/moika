package io.khasang.moika.service.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.service.WashAddrDataAccessService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pauls on 01.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashAddrDataAccessServiceTest {
    @Autowired
    WashAddrDataAccessService addrDataAccessService;


    @Before
    public void initTests() {
        System.out.println("Wash Address unit tests are beginning...");
    }

    private final String existingAddrStreet = "Пресня";
    private final String newAddrStreet = "Princesse Luisa Allee";
    private final String existingCity = "Москва";
    private final String newCityName = "Omsk";

    @Test
    @Transactional
    public void testWashAddrList() {

        List<WashAddr> addrList = null;
        try {
            addrList = addrDataAccessService.getAllWashAddr();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("WashAddr list is null", addrList);
        Assert.assertFalse("WashAddr list is empty", addrList.isEmpty());
        boolean isCityExists = false;
        for (WashAddr item : addrList) {
            if (item.getCity().getName().equalsIgnoreCase(existingCity)) {
                isCityExists = true;
            }
        }
        Assert.assertTrue("WashAddr list not contain " + existingCity, isCityExists);
    }

    @Test
    @Transactional
    public void testGetWashAddrById() {

        WashAddr washAddr = null;
        try {
            washAddr = addrDataAccessService.getWashAddrById(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("WashAddr is null", washAddr);
        Assert.assertTrue("WashAddr  not contain street " + existingAddrStreet, washAddr.getStreet().equalsIgnoreCase(existingAddrStreet));
        Assert.assertTrue("WashAddr  not contain city " + existingCity, washAddr.getCity().getName().equalsIgnoreCase(existingCity));
    }


    @Test
    @Transactional
    public void testAddWashAddrService () {
        final BigDecimal lat =  new BigDecimal("53.32145").setScale(5);
        final BigDecimal lon = new BigDecimal("45.25473").setScale(5);

        WashAddr washAddr = new WashAddr(); // подготовили класс для тестирования
        City newCity = new City(newCityName);
        washAddr.setCity(newCity);
        washAddr.setStreet(newAddrStreet);
        washAddr.setBuilding("233");
        washAddr.setCoordinate(new Coordinate(lat,lon ));

        WashAddr  newWashAddr = null;
        try {
            newWashAddr = addrDataAccessService.addWashAddr(washAddr);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("WashAddr is null", washAddr);
        Assert.assertTrue("WashAddr  not contain street " + newAddrStreet, newWashAddr.getStreet().equalsIgnoreCase(newAddrStreet));
        Assert.assertEquals("WashAddr not equals coordianate lat " +lat.toString(), newWashAddr.getCoordinate().getLat(), lat);
        Assert.assertEquals("WashAddr not equals coordianate lon " + lon.toString(), newWashAddr.getCoordinate().getLon(), lon);
        Assert.assertTrue("WashAddr  not contain city " + newCityName, newWashAddr.getCity().getName().equalsIgnoreCase(newCityName));

    }
}
