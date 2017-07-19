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
    public Orders createOrder(Orders order) throws MoikaDaoException {
        return ordersDao.create(order);
    }

    @Override
    public Orders getOrder(long id) throws MoikaDaoException {
        return ordersDao.get(id);
    }

    @Override
    public Orders updateOrder(Orders order) throws MoikaDaoException {
        return ordersDao.update(order);
    }

    @Override
    public boolean deleteOrder(Orders order) throws MoikaDaoException {
        Orders orderToDelete = getOrder(order.getId());
        if (orderToDelete != null) {
            try {
                ordersDao.delete(orderToDelete);
                return true;
            } catch (MoikaDaoException e) {
                return false;
            }
        } else
            return false;
    }

    @Override
    public List<Orders> getAllOrders(int idFclt) throws MoikaDaoException {
        return ordersDao.getAllOrders(idFclt);
    }

}
