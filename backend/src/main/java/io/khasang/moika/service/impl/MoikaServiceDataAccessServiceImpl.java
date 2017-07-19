package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.EServiceType;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


@Service(value = "moikaServiceDataAccessService")
@Transactional
public  class MoikaServiceDataAccessServiceImpl<T extends MoikaService> implements MoikaServiceDataAccessService<T> {

    final MoikaServiceDao moikaServiceDao;

    protected Class<? extends T> daoType;

    @Autowired
    public MoikaServiceDataAccessServiceImpl(MoikaServiceDao moikaServiceDao) {
        this.moikaServiceDao = moikaServiceDao;
        daoType = this.moikaServiceDao.getDaoType();
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
    public List<T> getServices(int idFclt) throws MoikaDaoException {
        return moikaServiceDao.getServices(idFclt);
    }


    @Override
    public List<T> getServicesByStatus(int idFclt, int idStatus) throws MoikaDaoException {
        return moikaServiceDao.getServicesByStatus(idFclt, idStatus);
    }

    @Override
    public List<T> getervicesByStatus(int idFclt, String status) throws MoikaDaoException {
        return moikaServiceDao.getServicesByStatus(idFclt, status);
    }

    @Override
    public List<T> getServicesByType(int idFclt, String typeCode) throws MoikaDaoException {
        return moikaServiceDao.getServicesByType(idFclt, typeCode);
    }

    @Override
    public List<T> getServicesByType(int idFclt, EServiceType serviceType) throws MoikaDaoException {
        return moikaServiceDao.getServicesByType(idFclt, serviceType);
    }

    @Override
    public List<T> getActualServices(int idFclt) throws MoikaDaoException {
        return moikaServiceDao.getActualServices(idFclt);
    }

    @Override
    public List<T> getServicesByGroup(int idFclt, EServiceGroup serviceGroup) throws MoikaDaoException {
        return moikaServiceDao.getServicesByGroup(idFclt, serviceGroup);
    }

    @Override
    public List<T> getServicesByGroup(int idFclt, String groupCode) throws MoikaDaoException {
        return moikaServiceDao.getServicesByGroup(idFclt, groupCode);
    }
}
