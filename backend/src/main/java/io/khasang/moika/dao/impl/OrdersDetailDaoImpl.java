package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.Orders;
import io.khasang.moika.entity.OrdersDetail;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrdersDetailDaoImpl extends MoikaDaoCrudImpl<OrdersDetail> implements OrdersDetailDao {
    private SessionFactory sessionFactory;

    public OrdersDetailDaoImpl() {
    }

    @Override
    public List<OrdersDetail> getOrdersDetailForOrders(Orders idOrder) {
        return null;
    }
}
