package io.khasang.moika.controller;

import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.ClientStatus;
import io.khasang.moika.service.ClientDataAccessService;
import io.khasang.moika.service.ClientStatusDataAccessService;
import io.khasang.moika.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ClientStatusDataAccessService clientStatusDataAccessService;


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

    /**
     * Обновление автомобиля
     * @param client автомобиль для добавления
     * @return сохранённый автомобиль
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateClient(@ModelAttribute(value = "company") Client client){
        client = clientDataAccessService.updateClient(client);
        if (client == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return client;
    }

    /**
     * Удаления автомобиля
     * @param id автомобиля для удаления
     * @return redirect
     */
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteClient(@PathVariable("id") String id){
        Client client = clientDataAccessService.getClientById(Long.parseLong(id));
        if (client != null) {
            if (clientDataAccessService.deleteClient(client))
              return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Возвращение автомобиля по id
     * @param id автомобиль для добавления
     * @return  автомобиль по id
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientById(@PathVariable("id") String id){
        Client client =clientDataAccessService.getClientById(Long.parseLong(id));
        if (client == null)
            return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return client;
    }
    /**
     * Клиенты мойки
     * @param idFclt ID иойки
     * @return  автомобили
     */

    @RequestMapping(value = "/onFclt/{idFclt}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientOnFclt(@PathVariable("idFclt") String idFclt){
        List<Client> clients = clientDataAccessService.getClientsOnFacility(Integer.parseInt(idFclt));
        if (clients == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clients;
    }
    
    /**
     * Возвращение автомобиля по типу кузова
     * @param clientStatus тип кузова
     * @return  автомобили
     */
    @RequestMapping(value = "/byStatus/{code}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientsByStatus(@PathVariable("code") String clientStatus) {
        List<Client>  clients =  clientDataAccessService.getClientsByStatus(clientStatus);;
        if (clients == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clients;
    }

       /**
     * Получение списка кдиентов по коду
     * @return
     */
    @RequestMapping(value = "/clients", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object  getClientsList(){
        List<Client>  clients =  clientDataAccessService.getAllClients();
        if (clients == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clients;
    }
    /**
     * Возвращение клиентов автомобиля 
     * @param carNum № авто
     * @return  автомобили
     */
    @RequestMapping(value = "/byCar/{num}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientsByCar(@PathVariable("num") String carNum) {
        List<Client>  clients =  clientDataAccessService.getClientsByCarNum(carNum);;
        if (clients == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clients;
    }
    
    /**
     * Получение списка типа клтентов по коду
     * @param code 
     * @return
     */
    @RequestMapping(value = "/status/byCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientStatuses(@PathVariable(value = "code") String code) {
        ClientStatus clientStatus =  (ClientStatus) clientStatusDataAccessService.getStatusByCode(code);
        if (clientStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clientStatus;
    }

    /**
     * Получение списка типов машин
     * @return
     */
    @RequestMapping(value = "/status/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientStatussList() {
        List<ClientStatus> clientStatusList = clientStatusDataAccessService.getAllStatuses();
        if (clientStatusList == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clientStatusList;
    }

    /**
     * тип машин по коду
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/status/byId/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object getClientStatusById(@PathVariable(value = "id") int id) {
        ClientStatus clientStatus = (ClientStatus) clientStatusDataAccessService.getStatusById(id);
        if (clientStatus == null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return clientStatus;
    }

    /**
     * Добавление нового типа машин
     *
     * @return
     */
    @RequestMapping(value = "/status/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addNewBoxStatus(@RequestBody ClientStatus newClientStatus) {
        ClientStatus clientStatus = (ClientStatus) clientStatusDataAccessService.addStatus(newClientStatus);
        if (clientStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return clientStatus;
    }

    /**
     * Обновление нового типа машин
     *
     * @return
     */
    @RequestMapping(value = "/status/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateBoxStatus(@RequestBody ClientStatus newClientStatus) {
        ClientStatus clientStatus =  (ClientStatus) clientStatusDataAccessService.updateStatus(newClientStatus);
        if (clientStatus == null)
            return  new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        else
            return clientStatus;
    }

    /**
     * Удаление типа машин
     *
     * @return -  HTTPResponce status
     */
    @RequestMapping(value = "/status/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteBoxStatus(@PathVariable(value = "id") String inputId) {
        ClientStatus clientStatus = (ClientStatus) clientStatusDataAccessService.getStatusById(Integer.valueOf(inputId));
        if (clientStatus != null) {
            int id = clientStatus.getId();
            clientStatusDataAccessService.deleteStatus(clientStatus);
            return  new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
