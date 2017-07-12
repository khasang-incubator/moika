package io.khasang.moika.entity;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.CityDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashFacilityDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashFacilityEntityTest {

    @Autowired
    WashFacilityDao facilityDao;

    @Autowired
    CityDao cityDao;

    final int id = 16;
    final String fcltName = "Test2 REST мойка";
    final String existingFasity = "Test2 REST мойка";
    final String statusCode = "WORKING";
    final String typeCode = "MEDIUM";


    @Ignore
    @Before
    public void initTests() {
        System.out.println("WashFacilityEntityTest are beginning...");
    }


    @Test
    @Transactional
    public void testWashFacilityList() {
        List<WashFacility> resFcltList = null;
        try {
            resFcltList = facilityDao.getAll();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Request body does not contain WashFacilities List", resFcltList);
        Assert.assertFalse("WashFacilities List is empty", resFcltList.isEmpty());

        WashFacility resFclt = resFcltList.get(0);
        Assert.assertNotNull("Could not get any facility from list", resFclt);

        Assert.assertTrue("Facility does  not contain boxes", resFclt.getWashBoxes().size() > 0);
        WashBox resBox = (new ArrayList<WashBox>(resFclt.getWashBoxes()).get(0));
        Assert.assertNotNull("Could not get any box", resBox);
        BoxStatus bs = resBox.getBoxStatusEntity();
        Assert.assertNotNull("Could not get any box status entity", bs);
        BoxType bt = resBox.getBoxTypeEntity();
        Assert.assertNotNull("Could not get any box type entity", bt);
    }

    @Test
    @Transactional
    public void testGetFcltById() {
        WashFacility resFclt = null;
        try {
            resFclt = facilityDao.get(id);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Request body does not contain WashFacilities", resFclt);

        Assert.assertTrue("Facility does not exist " + existingFasity, resFclt.getName().equalsIgnoreCase(existingFasity));
        boolean isBox = false;

        Assert.assertTrue("Facility does  not contain boxes", resFclt.getWashBoxes().size() > 0);
        List<WashBox> resBoxList = new ArrayList<WashBox>(resFclt.getWashBoxes());
        Assert.assertEquals("Facility box list is not 4 item length", 4 , resBoxList.size());
        WashBox box = resBoxList.get(0);
        if (box != null) {
            Assert.assertTrue("Facility  box status not " + statusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(statusCode));
            Assert.assertTrue("Facility  box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
        }
        else Assert.assertTrue("Facility does not contain box", isBox);
    }


    @Test
    @Transactional
    public void testGetFcltByCityId() {
        City aCity = cityDao.get(1);
        Assert.assertNotNull("Cann`t continue testGetFcltByCityId. City is Null", aCity);
        List<WashFacility> resFcltList = null;
        try {
            resFcltList = facilityDao.getWashFacilitiesInCity(aCity);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Request body does not contain WashFacilities List", resFcltList);
        Assert.assertFalse("WashFacilities List is empty", resFcltList.isEmpty());

        WashFacility resFclt = resFcltList.get(0);
        Assert.assertNotNull("Could not get any facility from list", resFclt);

        Assert.assertTrue("Facility does  not contain boxes", resFclt.getWashBoxes().size() > 0);
        WashBox resBox = (new ArrayList<WashBox>(resFclt.getWashBoxes()).get(0));
        Assert.assertNotNull("Could not get any box", resBox);
        BoxStatus bs = resBox.getBoxStatusEntity();
        Assert.assertNotNull("Could not get any box status entity", bs);
        BoxType bt = resBox.getBoxTypeEntity();
        Assert.assertNotNull("Could not get any box type entity", bt);
    }


    @Test
    @Transactional
    public void testGetFcltCalendar() {
        WashFacility resFclt = null;
        try {
            resFclt = facilityDao.get(id);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Request body does not contain WashFacilities", resFclt);

        Assert.assertTrue("Facility does not exist " + existingFasity, resFclt.getName().equalsIgnoreCase(existingFasity));
        boolean isCalendar = false;

     //   List<WashFacilityCalendar> fcltOddDays = resFclt.getFcltOddOffDays();
     //   Assert.assertTrue("Facility calendar is empty", fcltOddDays.size() > 0);

     //   Assert.assertNotNull("Not found default work hours", resFclt.getDefaultWorkHours() );
    }
}
