package io.khasang.moika.service.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.service.OrdermAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdermAccessServiceImplTest {
    @Autowired
    OrdermAccessService ordermAccessService;

    @Test
    public void commonOrderm() throws Exception {
        Orderm orderm = new Orderm("1");
        ordermAccessService.addOrderm(orderm);
        long id = orderm.getId();
        orderm = new Orderm("2");
        ordermAccessService.addOrderm(orderm);
        orderm = ordermAccessService.getOrderm(id);
        orderm.setNumber("11");
        ordermAccessService.updateOrderm(orderm);
        orderm = new Orderm("33");
        orderm = ordermAccessService.addOrderm(orderm);
        ordermAccessService.deleteOrderm(orderm);
        List<Orderm> l = ordermAccessService.getAllOrderm();
    }

}