package io.khasang.moika.dao;

import io.khasang.moika.entity.Orders;

import java.util.List;


public interface OrdersDao extends IMoikaDaoCrud<Orders> {
    List<Orders> getAllOrders(int idFclt);
}
