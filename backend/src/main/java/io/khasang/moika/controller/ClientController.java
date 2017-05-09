package io.khasang.moika.controller;

import io.khasang.moika.entity.Client;
import io.khasang.moika.service.ClientDataAccessService;
import io.khasang.moika.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/client")
public class ClientController {
//    @Autowired
//    private ClientDao clientDAO;
    @Autowired
    private ClientDataAccessService clientDataAccessService;
    @Autowired
    private ClientValidator clientValidator;


    @RequestMapping(method =  RequestMethod.GET)
    public String getClient(){
        return "client";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Client> getClientsList(Model model) {
        List<Client> clientList = clientDataAccessService.getAllClients();
        return clientList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addClient(@RequestBody Client client, Model model){
        if(client!=null){
            model.addAttribute("client",client);
        }
        return "client";
    }

}
