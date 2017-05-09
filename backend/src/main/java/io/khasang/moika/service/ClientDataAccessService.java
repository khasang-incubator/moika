package io.khasang.moika.service;

import io.khasang.moika.entity.Client;

import java.util.List;

public interface ClientDataAccessService {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);
    Client getClientById(int id);
    List<Client> getAllClients();
    List<Client> getClientsByStatus(int idStatus);
    List<Client> getClientsByStatus(String statusCode);
    List<Client> getClientsOnFacility(int idFclt);
}
