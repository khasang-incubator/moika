package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.OrdersDao;
import io.khasang.moika.entity.Orders;
import io.khasang.moika.service.OrdersAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("ordersAccessServiceImpl")
@Transactional
public class OrdersAccessServiceImpl implements OrdersAccessService {
    @Qualifier("ordersDaoImpl")
    @Autowired
    OrdersDao ordersDao;

    @Override
    public Orders create(Orders entity) throws MoikaDaoException {
        return ordersDao.create(entity);
    }

    @Override
    public Orders get(long id) throws MoikaDaoException {
        return ordersDao.get(id);
    }

    @Override
    public Orders get(int id) throws MoikaDaoException {
        return ordersDao.get(id);
    }


    @Override
    public Orders update(Orders entity) throws MoikaDaoException {
        return ordersDao.update(entity);
    }

    @Override
    public Orders update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        return null;
    }

    @Override
    public Orders delete(Orders entity) throws MoikaDaoException {
        return ordersDao.delete(entity);
    }

    @Override
    public List<Orders> getAll() throws MoikaDaoException {
        return ordersDao.getAll();
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
}
