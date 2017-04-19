package io.khasang.moika.service;

import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.Orders;
import io.khasang.moika.entity.OrdersDetail;

import java.util.List;

public interface OrdersDetailAccessService extends OrdersDetailDao {

    List<OrdersDetail> getOrdersDetailForOrders(Orders idOrder);
}
