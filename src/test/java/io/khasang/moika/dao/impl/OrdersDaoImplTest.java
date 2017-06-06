package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.*;
import io.khasang.moika.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdersDaoImplTest {
    @Qualifier("ordersDaoImpl")
    @Autowired
    OrdersDao ordersDao;

    @Qualifier("ordersDetailDaoImpl")
    @Autowired
    OrdersDetailDao ordersDetailDao;

    @Autowired
    CarDao carDao;

    @Autowired
    ClientDao clientDao;

    @Autowired
    WashFacilityDao washFacilityDao;

    @Autowired
    MoikaServiceDao moikaServiceDao;

    @Test
    public void addOrders() throws Exception {
        OrdersDetail detail, temp;
        Orders chekOrder = null;

        Orders orders =new Orders();
        orders.setIdCar(1L);
        Car car = carDao.get(1L);
        orders.setCar(car);

        orders.setIdClient(1L);
        Client client  = clientDao.get(orders.getIdClient());
        orders.setClient(client);

        orders.setStartSum(new BigDecimal("500.00"));
        orders.setDiscountSum(new BigDecimal("50.00"));
        orders.setFinalSum(new BigDecimal("450.00"));

        WashFacility washFacility = washFacilityDao.get(1L);
        orders.setWashFacility(washFacility);

        detail = new OrdersDetail();
        MoikaService moikaService = (MoikaService)moikaServiceDao.get(1L);
      //  moikaService.getServiceAddInfo()getServiceAddInfo().getServiceCost();
        detail.setWashservice(moikaService);
        detail.setServiceCost(new BigDecimal("500.00"));

        try {
            chekOrder = ordersDao.create(orders);
        }
        catch (MoikaDaoException e){
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Order is null", chekOrder);

        Assert.assertEquals("Order not cost", new BigDecimal("450.00").setScale(2), chekOrder.getFinalSum().setScale(2));
    }

    @Test
    public void getOrdersList() throws Exception {
  /*      long id = orders.getId();
        orders = ordersDao.get(2);
        orders = ordersDao.update(orders);
        List<Orders> list = ordersDao.getAll();
        orders = ordersDao.getOrders("3");
        orders = ordersDao.delete(orders);
        orders = ordersDao.getOrders("1");

        detail=new OrdersDetail(new BigDecimal(1), new BigDecimal(1000));
        temp = ordersDetailDao.create(orders, detail);
        detail=new OrdersDetail(new BigDecimal(2), new BigDecimal(2000));
        ordersDetailDao.create(orders, detail);
        List<OrdersDetail> list1= ordersDetailDao.getOrdersDetailForOrders(orders);
        ordersDetailDao.delete(orders, temp);
        list1= ordersDetailDao.getOrdersDetailForOrders(orders); */
    }
}