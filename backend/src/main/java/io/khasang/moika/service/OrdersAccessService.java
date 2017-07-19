package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.OrdersDao;
import io.khasang.moika.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersAccessService {


   Orders createOrder(Orders order) throws MoikaDaoException;
   Orders getOrder(long id) throws MoikaDaoException;
   Orders updateOrder(Orders order) throws MoikaDaoException;
   boolean deleteOrder(Orders order) throws MoikaDaoException;
   List<Orders> getAllOrders(int idFclt) throws MoikaDaoException;
}
