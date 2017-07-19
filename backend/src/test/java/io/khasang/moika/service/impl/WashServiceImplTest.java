package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.CarTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.CarType;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashServiceImplTest {


    @Autowired
    WashServiceDataAccessService washerviceDAS;
    @Autowired
    ServiceTypeDao serviceTypeDao;
    @Autowired
    ServiceStatusDao serviceStatusDao;

    @Autowired
    WashServiceDataAccessService washServiceDAS;

    @Autowired
    CarTypeDao carTypeDao;

    final String serviceName = "Мойка силой мысли";
    final String testDescr = "к нам на полставки устроился Йода";
    final int idFclt = 3;

    @Test
    @Transactional
    public void testWashServiceListFromService() {
        List<WashService> serviceList = null;
        try {
            serviceList = washerviceDAS.getServicesByType(idFclt,"WASH");
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (WashService item : serviceList) {
            if (item.getId() == 11) {
                isWashCode = true;
                if (item.getCarTypeEntity().getTypeCode().equals("CAR")) {
                    cost = item.getServiceCost();
                    dur = item.getServiceDuration();
                    break;
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }

    @Test
    @Transactional
    public void testWashServiceList() {
        List<WashService> serviceList = null;
        try {
            serviceList = washServiceDAS.getServicesByType(idFclt,"WASH");
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Wash Service  list is null", serviceList);
        Assert.assertFalse("Wash Service  list is empty", serviceList.isEmpty());
        BigDecimal cost = null;
        int dur = 0;
        for (WashService item : serviceList) {
            Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"",
                    item.getServiceType().getTypeCode().equalsIgnoreCase("WASH"));
            if (item.getCarTypeEntity().getTypeCode().equals("CAR")) {
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
            }
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("350.00").setScale(2), cost);
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 1200, dur);
        }
    }

    @Test
    @Transactional
    public void testAddWashService() {

        WashService washService = new WashService(); // подготовили объект для тестирования

        washService.setName(serviceName);
        washService.setIdFacility(idFclt);
        washService.setDescription(testDescr);
        ServiceType stEntity = serviceTypeDao.getEntityByCode("WASH");
        washService.setServiceType(stEntity);
        ServiceStatus stsEntity = serviceStatusDao.getEntityByCode("PLAN");
        washService.setServiceStatus(stsEntity);

        CarType carType = carTypeDao.getEntityByCode("CAR");
        washService.setCarTypeEntity(carType);
        washService.setServiceCost(new BigDecimal("3500.00"));
        washService.setServiceDuration(30);

        WashService testService = new WashService(); // подготовили объект для возврата
        try {
            testService = washerviceDAS.addService(washService);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Wash servise is null", testService);

        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", testService.getName().equalsIgnoreCase(serviceName));
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", testService.getCarTypeEntity().getTypeCode().equals("CAR"));
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("3500.00").setScale(2), testService.getServiceCost().setScale(2));
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 10L, testService.getServiceDuration().longValue());
    }
}

