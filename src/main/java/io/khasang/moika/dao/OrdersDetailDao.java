package io.khasang.moika.dao;

import io.khasang.moika.entity.Orders;
import io.khasang.moika.entity.OrdersDetail;

import java.util.List;

public interface OrdersDetailDao extends IMoikaDaoCrud<OrdersDetail> {

     List<OrdersDetail> getOrdersDetailForOrders(Orders idOrder);
}
