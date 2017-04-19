package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdersDao;
import io.khasang.moika.dao.OrdersDetailDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdersDetailDaoImplTest {
    @Qualifier("ordersDetailDaoImpl")
    @Autowired
    OrdersDetailDao ordersDetailDao;
    @Qualifier("ordersDaoImpl")
    @Autowired
    OrdersDao ordersDao;

    @Test
    public void commonOrdersDetail() throws Exception {
     /*   OrdersDetail detail, temp, temp1;

        Orders orders = new Orders("1");
        ordersDao.create(orders);


        detail = new OrdersDetail(new BigDecimal(1), new BigDecimal(1000));
        temp1 = ordersDetailDao.create(orders, detail);
        detail = new OrdersDetail(new BigDecimal(2), new BigDecimal(2000));
        temp = ordersDetailDao.create(orders, detail);
        detail = new OrdersDetail(new BigDecimal(3), new BigDecimal(3000));
        ordersDetailDao.create(orders, detail);
        List<OrdersDetail> list1 = ordersDetailDao.getOrdersDetailForOrders(orders);
        ordersDetailDao.delete(orders, temp);
        list1 = ordersDetailDao.getOrdersDetailForOrders(orders);
        detail = ordersDetailDao.get(temp1.getId());
        detail.setSumOfWork(new BigDecimal(777));
        ordersDetailDao.update(detail);
 */
    }
}