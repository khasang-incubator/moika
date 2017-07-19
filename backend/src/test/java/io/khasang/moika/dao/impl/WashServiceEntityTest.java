package io.khasang.moika.dao.impl;


import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.WashService;
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
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class WashServiceEntityTest {

    @Autowired
    WashServiceDao serviceDao;

    final int existedDur = 300;
    final BigDecimal existedCost = new BigDecimal("300.00").setScale(2);

    @Test
    @Transactional
    public void testWashServiceList() {
        List<WashService> serviceList = null;
        try {
            serviceList = serviceDao.getServices(3);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (WashService item : serviceList) {
            if (item.getCarTypeEntity().getTypeCode().equalsIgnoreCase("CAR")) {
                isWashCode = true;
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
                break;
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", existedCost, cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", existedDur, dur);
    }
}
