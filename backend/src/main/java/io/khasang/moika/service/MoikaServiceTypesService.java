package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;

import java.util.List;

public interface MoikaServiceTypesService {
    ServiceType addServiceType(ServiceType entity) throws MoikaDaoException;
    void updateServiceType(ServiceType entity) throws MoikaDaoException;
    void deleteServiceType(ServiceType entity) throws MoikaDaoException;
    ServiceType getServiceTypeByID(int id) throws MoikaDaoException;
    ServiceType getServiceTypeByCode(String code) throws MoikaDaoException;
    List<ServiceType> getAllServiceTypes() throws MoikaDaoException;

}
