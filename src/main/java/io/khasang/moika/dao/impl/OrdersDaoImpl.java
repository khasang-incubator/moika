package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdersDao;
import io.khasang.moika.entity.Orders;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrdersDaoImpl extends MoikaDaoCrudImpl<Orders> implements OrdersDao {
    private SessionFactory sessionFactory;

    public OrdersDaoImpl() {
    }


}
