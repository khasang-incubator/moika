package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceStatus;

import java.util.List;

public interface MoikaServiceStatusService {
    ServiceStatus addServiceStatus(ServiceStatus entity) throws MoikaDaoException;
    void updateServiceStatus(ServiceStatus entity) throws MoikaDaoException;
    void deleteServiceStatus(ServiceStatus entity) throws MoikaDaoException;
    ServiceStatus getServiceStatusByID(int id) throws MoikaDaoException;
    ServiceStatus getServiceStatusByCode(String code) throws MoikaDaoException;
    List<ServiceStatus> getAllServiceStatuses() throws MoikaDaoException;

}
