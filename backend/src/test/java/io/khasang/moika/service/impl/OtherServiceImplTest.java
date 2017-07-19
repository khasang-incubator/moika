package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.OtherService;
import io.khasang.moika.service.OtherServiceDataAccessService;
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
public class OtherServiceImplTest {

    @Autowired
    OtherServiceDataAccessService otherServiceDAS;

    final String serviceName = "Прочий сервис силой мысли";
    final String testDescr = "к нам на полставки устроился Люк Сковородкин";
    final int idFclt = 3;

    @Test
    @Transactional
    public void testCleanServiceList() {
        List<OtherService> serviceList = null;
        try {
            serviceList = otherServiceDAS.getServicesByType(idFclt,"OTHER");
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (OtherService item : serviceList) {
            if (item.getServiceType().getTypeCode().equalsIgnoreCase("OTHER")) {
                isCode = true;
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
                break;
            }
        }
        Assert.assertTrue("Service types list not contain name \"Прочее\"", isCode);
        Assert.assertEquals("Service types list not contain name \"Прочее\"", new BigDecimal("200.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Прочее\" not last", 120, dur);
    }
}
