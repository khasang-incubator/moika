package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdersDao;
import io.khasang.moika.entity.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrdersDaoImpl extends MoikaDaoCrudImpl<Orders> implements OrdersDao {
    private SessionFactory sessionFactory;

    public OrdersDaoImpl() {
    }

    @Override
    public List<Orders> getAllOrders(int idFclt) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from orders where idFclt = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }
}
