package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.PolishService;
import io.khasang.moika.service.PolishServiceDataAccessService;
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
public class PolishServiceImplTest {

    @Autowired
    PolishServiceDataAccessService polisServiceDAS;

    final String serviceName = "Полировка силой мысли";
    final String testDescr = "к нам на полставки устроился Люк Сковородкин";
    final int idFclt = 3;


    @Test
    @Transactional
    public void testCleanServiceList() {
        List<PolishService> serviceList = null;
        try {
            serviceList = polisServiceDAS.getServicesByType(idFclt,"POLISH");
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (PolishService item : serviceList) {
            if (item.getServiceType().getTypeCode().equalsIgnoreCase("POLISH")) {
                isCode = true;
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
                break;
            }
        }
        Assert.assertTrue("Service types list not contain name \"Химчиска салона\"", isCode);
        Assert.assertEquals("Service types list not contain name \"Химчиска салона\"", new BigDecimal("1000.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }
}
