package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "moikaServiceDataAccessService")
@Transactional
public  class MoikaServiceDataAccessServiceImpl<T extends MoikaService> implements MoikaServiceDataAccessService<T> {
    @Autowired
    MoikaServiceDao moikaServiceDao;


    public MoikaServiceDataAccessServiceImpl(MoikaServiceDao moikaServiceDao) {
        this.moikaServiceDao = moikaServiceDao;
    }

    public MoikaServiceDataAccessServiceImpl() {
    }


    @Override
    public T addService(T service) throws MoikaDaoException {
        return (T)moikaServiceDao.create(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getServiceById(int id) throws MoikaDaoException {
        return (T)moikaServiceDao.get(id);
    }

    @Override
    public void updateService(T service) throws MoikaDaoException {
        moikaServiceDao.update(service);
    }

    @Override
    public T deleteService(T service)  throws MoikaDaoException {
        moikaServiceDao.delete(service);
        return service;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getAllServices() throws MoikaDaoException {
        return moikaServiceDao.getAll();
    }


    @Override
    public List<T> getAllervicesByStatus(int idStatus) throws MoikaDaoException {
            return moikaServiceDao.getServicesByStatus(idStatus);
    }

    @Override
    public List<T> getAllervicesByStatus(String status) throws MoikaDaoException {
        return moikaServiceDao.getServicesByStatus(status);
    }

    @Override
    public List<T> getServicesByType(String typeCode) throws MoikaDaoException {
        return moikaServiceDao.getServicesByType(typeCode);
    }

    @Override
    public List<T> getActualServices() throws MoikaDaoException {
        return moikaServiceDao.getActualServices();
    }

    @Override
    public List<T> getServicesByCarType(String carTypeCode) throws MoikaDaoException{
         return moikaServiceDao.getServicesByCarType(carTypeCode);
    }

    @Override
    public List<T> getServicesOnFacility(int idFclt) throws MoikaDaoException {
        return moikaServiceDao.getServicesOnFacility(idFclt);
    }

    @Override
    public List<T> getActualServicesOnFacility(int idFclt) throws MoikaDaoException {
        return moikaServiceDao.getActualServicesOnFacility(idFclt);
    }
}
