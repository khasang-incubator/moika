package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.Orders;
import io.khasang.moika.entity.OrdersDetail;
import io.khasang.moika.service.OrdersDetailAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("ordersDetailAccessServiceImpl")
@Transactional
public class OrdersDetailAccessServiceImpl implements OrdersDetailAccessService {

    @Qualifier("ordersDetailDaoImpl")
    @Autowired
    OrdersDetailDao ordersDetailDao;

    public OrdersDetailAccessServiceImpl() {
    }

    @Override
    public OrdersDetail create(OrdersDetail ordersDetail) {
        return ordersDetailDao.create(ordersDetail);
    }


    @Override
    public List<OrdersDetail> getOrdersDetailForOrders(Orders idOrder) {
        return ordersDetailDao.getOrdersDetailForOrders(idOrder);
    }


    @Override
    public OrdersDetail get(long id) throws MoikaDaoException {
        return ordersDetailDao.get(id);
    }


    @Override
    public OrdersDetail update(OrdersDetail entity) throws MoikaDaoException {
        return ordersDetailDao.update(entity);
    }

    @Override
    public OrdersDetail update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        return ordersDetailDao.update(id, fieldValueMap);
    }

    @Override
    public OrdersDetail delete(OrdersDetail entity) throws MoikaDaoException {
        return ordersDetailDao.delete(entity);
    }

    @Override
    public List<OrdersDetail> getAll() throws MoikaDaoException {
        return ordersDetailDao.getAll();
    }

    @Override
    public Session getCurrentSession() {
        return ordersDetailDao.getCurrentSession();
    }

    @Override
    public OrdersDetail get(int id) throws MoikaDaoException {
        return ordersDetailDao.get(id);
    }
}
