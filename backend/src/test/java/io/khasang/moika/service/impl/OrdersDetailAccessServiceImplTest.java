package io.khasang.moika.service.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.service.OrdersAccessService;
import io.khasang.moika.service.OrdersDetailAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdersDetailAccessServiceImplTest {
    @Autowired
    OrdersAccessService ordermAccessService;
    @Autowired
    OrdersDetailAccessService ordersDetailAccessService;

    @Test
    public void commonOrderm() throws Exception {
   /*     Orderm orderm = new Orderm("1");
        ordermAccessService.create(orderm);
        OrdermDetail detail, temp, temp1;
        detail = new OrdermDetail(new BigDecimal(1), new BigDecimal(1000));
        temp1 = ordersDetailAccessService.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(2), new BigDecimal(2000));
        temp = ordersDetailAccessService.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(3), new BigDecimal(3000));
        ordersDetailAccessService.create(orderm, detail);
        List<OrdermDetail> list1 = ordersDetailAccessService.getOrdermDetailForOrderm(orderm);
        ordersDetailAccessService.delete(orderm, temp);
        list1 = ordersDetailAccessService.getOrdermDetailForOrderm(orderm);
        detail = ordersDetailAccessService.get(temp1.getId());
        detail.setSumOfWork(new BigDecimal(777));
        ordersDetailAccessService.update(detail);*/
    }

}