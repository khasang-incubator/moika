package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "washServiceDataAccessService")
@Transactional
public class WashServiceDataAccessServiceImpl extends MoikaServiceDataAccessServiceImpl<WashService> implements WashServiceDataAccessService {
    @Autowired
    WashServiceDao serviceDao;

    @Autowired
    public WashServiceDataAccessServiceImpl( @Qualifier("washServiceDao")WashServiceDao wasServiceDao) {
        super(wasServiceDao);
        this.serviceDao = wasServiceDao;
    }

    @Override
    public List<WashService> getWashServices(int idFclt) throws MoikaDaoException {
        return serviceDao.getWashServices(idFclt);
    }

    @Override
    public List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException {
        return serviceDao.getServicesByCarType(idFclt, carTypeCode);
    }

    @Override
    public List<WashService> getWashServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup) {
        return serviceDao.getWashServiceByServiceGroup(idFclt, serviceGroup );
    }

    @Override
    public List<WashService> getWashServiceByServiceGroup(int idFclt, String groupCode) {
        return serviceDao.getWashServiceByServiceGroup(idFclt, groupCode );
    }

    @Override
    public WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, int idCarType) {
        return serviceDao.getWashServiceByGroupAndCarType(idFclt, idGroup, idCarType);
    }

    @Override
    public WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, String carTypeCode) {
        return serviceDao.getWashServiceByGroupAndCarType(idFclt, idGroup, carTypeCode);
    }
}
