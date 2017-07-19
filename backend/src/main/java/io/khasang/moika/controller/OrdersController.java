package io.khasang.moika.controller;

import io.khasang.moika.entity.Orders;
import io.khasang.moika.service.OrdersAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping(value ="api/orders/",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class OrdersController {
    @Autowired
    OrdersAccessService ordersAccessService;

    @RequestMapping(value = "list/{idFclt}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Object listOrder(@PathVariable(value = "idFclt") int idFclt) {
        List<Orders> ordersList = ordersAccessService.getAllOrders(idFclt);
        if ((ordersList == null) || (ordersList.isEmpty()))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return ordersList;
    }

    /**
     * добавление информации о заказе
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "add/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addWashBox(@RequestBody Orders order) {
        Orders resOrder = ordersAccessService.createOrder(order);
        if (resOrder == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resOrder;
    }

    /**
     * Обновление информации о заказе
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateOrder(@RequestBody Orders order) {
        Orders resOrder = ordersAccessService.updateOrder(order);
        if (resOrder == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resOrder;
    }

    /**
     * Выаод информаии о заказе по id
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public Object getOrderById(@PathVariable(value = "id") long orderId) {
        Orders resOrder = ordersAccessService.getOrder(orderId);
        if (resOrder == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return resOrder;
    }

    /**
     * Удаление заказа по id
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteWashBox(@PathVariable(value = "id") long orderId) {
        Orders resOrder = ordersAccessService.getOrder(orderId);
        if (resOrder != null) {
            long id = resOrder.getId();
            if (id != 0 ) {
                ordersAccessService.deleteOrder(resOrder);
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        }
           return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
