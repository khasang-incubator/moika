package io.khasang.moika.controller;

import io.khasang.moika.entity.Orders;
import io.khasang.moika.service.OrdersAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("orders/")
public class OrdersController {
    @Autowired
    OrdersAccessService ordersAccessService;

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String List(Model model) {
        List<Orders> listOrders = ordersAccessService.getAll();
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("nrows", "Количество заказов " + listOrders.size());
        return "orders-list";
    }
    @RequestMapping(value = "add/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("orders-edit-view-form", "orders", new Orders());
    }

    @RequestMapping(value = "add/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("orders") final Orders orders,
                         final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        ordersAccessService.create(orders);
        return "redirect:/orders/list/";
    }
}
