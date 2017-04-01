package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.service.MoikaServiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "moikaServiceStatussServiceImpl")
@Transactional
public class MoikaServiceStatusServiceImpl implements MoikaServiceStatusService {
    @Autowired
    ServiceStatusDao serviceStatusDao;

    public MoikaServiceStatusServiceImpl() {
    }

    @Override
    public ServiceStatus addServiceStatus(ServiceStatus entity) throws MoikaDaoException {
        return serviceStatusDao.create((ServiceStatus) entity);
    }

    @Override
    public void updateServiceStatus(ServiceStatus entity) throws MoikaDaoException {
        serviceStatusDao.update((ServiceStatus) entity);
    }

    @Override
    public void deleteServiceStatus(ServiceStatus entity) throws MoikaDaoException {
        serviceStatusDao.delete((ServiceStatus) entity);
    }

    @Override
    public ServiceStatus getServiceStatusByID(int id) throws MoikaDaoException {
        return serviceStatusDao.get(id);
    }

    @Override
    public ServiceStatus getServiceStatusByCode(String code) throws MoikaDaoException {
        return serviceStatusDao.getEntityByCode(code);
    }

    @Override
    public List<ServiceStatus> getAllServiceStatuses() throws MoikaDaoException {
        return serviceStatusDao.getAll();
    }
}
