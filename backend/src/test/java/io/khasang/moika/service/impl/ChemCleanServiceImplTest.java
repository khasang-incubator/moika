package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ChemCleanService;
import io.khasang.moika.service.ChemCleanServiceDataAccessService;
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
public class ChemCleanServiceImplTest {

    @Autowired
    ChemCleanServiceDataAccessService chemCleanServiceDAS;


    @Test
    @Transactional
    public void testChemCleanServiceList() {
        List<ChemCleanService> serviceList = null;
        try {
            serviceList = chemCleanServiceDAS.getServicesByType("CHEM_CLEAN");
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (ChemCleanService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Химчиска салона")) {
                isCode = true;
                if (item.getDirtTypeEntity().getTypeCode().equals("NORM")) {
                    if (item.getSalonMaterial().getTypeCode().equals("VELUR")) {
                        cost = item.getServiceCost();
                        dur = item.getServiceDuration();
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Химчиска салона\"", isCode);
        Assert.assertEquals("Service types list not contain name \"Химчиска салона\"", new BigDecimal("1000.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Химчиска салона\" not last", 300, dur);
    }
}