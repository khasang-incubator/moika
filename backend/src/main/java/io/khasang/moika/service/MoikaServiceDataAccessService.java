package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.EServiceType;
import io.khasang.moika.entity.MoikaService;

import java.util.List;

/**
 * Базовый интерфейс для всех моечных сервисов
 *
 */
public interface MoikaServiceDataAccessService<T extends MoikaService> {
    T addService(T service) throws MoikaDaoException;    //Create
    T getServiceById(int id)  throws MoikaDaoException;     //Read
    void updateService(T service)  throws MoikaDaoException;//Update
    T deleteService(T service)  throws MoikaDaoException; //Delete

    List<T> getServices(int idFclt) throws MoikaDaoException;
    List<T> getServicesByStatus(int idFclt, int idStatus) throws MoikaDaoException;
    List<T> getervicesByStatus(int idFclt, String status) throws MoikaDaoException;
    List<T> getServicesByType(int idFclt, String typeCode) throws MoikaDaoException;
    List<T> getServicesByType(int idFclt, EServiceType serviceType) throws MoikaDaoException;
    List<T> getActualServices(int idFclt) throws MoikaDaoException;
    List<T> getServicesByGroup(int idFclt, EServiceGroup serviceGroup) throws MoikaDaoException;
    List<T> getServicesByGroup(int idFclt, String groupCode) throws MoikaDaoException;
}
