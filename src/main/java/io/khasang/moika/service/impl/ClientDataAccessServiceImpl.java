package io.khasang.moika.service.impl;


import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.dao.ClientStatusDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.Client;
import io.khasang.moika.service.ClientDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("clientDaoServiceImpl")
@Transactional
public class ClientDataAccessServiceImpl implements ClientDataAccessService {
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientStatusDao clientStatusDao;


    public ClientDataAccessServiceImpl() {
    }

    @Override
    public Client addClient(Client client) {
        try {
            return clientDao.create(client);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        try {
            return clientDao.update(client);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteClient(Client client) {
        try {
            clientDao.delete(client);
            return true;
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client getClientById(long id) {
        try {
            return clientDao.get(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Client> getClientsByStatus(int idStatus) {
        try {
            return clientDao.getClientsByStatus(idStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getClientsByStatus(String statusCode) {
        try {
            return clientDao.getClientsByStatus(statusCode);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAllClients() {
        try {
            return clientDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getClientsOnFacility(int idFclt) {
        try {
            return clientDao.getClientsOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Client> getClientsByCarNum(String carNumber) {
        try {
            return clientDao.getClientsByCarNum(carNumber);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }
}