package io.khasang.moika.dao;

import io.khasang.moika.entity.Client;

import java.util.List;

/**
 * Интерфейс DAO для клиентов
 * @author Skvortsov Pavel
 *
 */
public interface ClientDao extends IMoikaDaoCrud<Client> {

    List<Client> getClientsByStatus(int idStatus);
    List<Client> getClientsByStatus(String statusCode);
    List<Client> getClientsOnFacility(int idFclt);
    List<Client> getClientsByCar(long idCar);
    List<Client> getClientsByCarNum(String carNumber);
}
