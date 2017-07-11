package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
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
    List<T> getAllServices()  throws MoikaDaoException;
    List<T> getAllervicesByStatus(int idStatus) throws MoikaDaoException;
    List<T> getAllervicesByStatus(String status) throws MoikaDaoException;
    List<T> getServicesByType(String typeCode) throws MoikaDaoException;
    List<T> getActualServices() throws MoikaDaoException;
    List<T> getServicesByCarType(String carTypeCode) throws MoikaDaoException;
    List<T> getServicesOnFacility(int idFclt) throws MoikaDaoException;
    List<T> getActualServicesOnFacility(int idFclt) throws MoikaDaoException;
}
