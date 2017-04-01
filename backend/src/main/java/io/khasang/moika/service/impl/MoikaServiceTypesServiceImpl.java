package io.khasang.moika.service.impl;

import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "moikaServiceTypesServiceImpl")
@Transactional
public class MoikaServiceTypesServiceImpl implements MoikaServiceTypesService {
    @Autowired
    ServiceTypeDao serviceTypeDao;

    public MoikaServiceTypesServiceImpl() {
    }

    @Override
    public ServiceType addServiceType(ServiceType entity) throws MoikaDaoException {
        return serviceTypeDao.create((ServiceType) entity);
    }

    @Override
    public void updateServiceType(ServiceType entity) throws MoikaDaoException {
        serviceTypeDao.update((ServiceType) entity);
    }

    @Override
    public void deleteServiceType(ServiceType entity) throws MoikaDaoException {
        serviceTypeDao.delete((ServiceType) entity);
    }

    @Override
    public ServiceType getServiceTypeByID(int id) throws MoikaDaoException {
        return serviceTypeDao.get(id);
    }

    @Override
    public ServiceType getServiceTypeByCode(String code) throws MoikaDaoException {
        return serviceTypeDao.getEntityByCode(code);
    }
    @Override
    public List<ServiceType> getAllServiceTypes() throws MoikaDaoException {
        return serviceTypeDao.getAll();
    }
}
