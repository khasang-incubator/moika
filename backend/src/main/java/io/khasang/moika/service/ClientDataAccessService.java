package io.khasang.moika.service;

import io.khasang.moika.entity.Client;

import java.util.List;

public interface ClientDataAccessService {
    Client addClient(Client client);
    Client updateClient(Client client);
    boolean deleteClient(Client client);
    Client getClientById(long id);
    List<Client> getAllClients();
    List<Client> getClientsByStatus(int idStatus);
    List<Client> getClientsByStatus(String statusCode);
    List<Client> getClientsOnFacility(int idFclt);
    List<Client> getClientsByCar(long idCar);
    List<Client> getClientsByCarNum(String carNumber);
}
