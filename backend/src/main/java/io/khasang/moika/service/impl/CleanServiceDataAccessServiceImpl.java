package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CleanServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.service.CleanServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "cleanServiceDataAccessService")
@Transactional
public class CleanServiceDataAccessServiceImpl extends MoikaServiceDataAccessServiceImpl<CleanService> implements CleanServiceDataAccessService {
    @Autowired
    CleanServiceDao cleanServiceDao;

    @Autowired
    public CleanServiceDataAccessServiceImpl(@Qualifier("cleanServiceDao") CleanServiceDao cleanServiceDao) {
        super(cleanServiceDao);
        this.cleanServiceDao = cleanServiceDao;
    }

    @Override
    public List<CleanService> getCleanServices(int idFclt) throws MoikaDaoException {
        return cleanServiceDao.getCleanServices(idFclt);
    }

    @Override
    public List<CleanService> getServicesByDirtType(int idFclt, String dirtTypeCode) throws MoikaDaoException {
        return cleanServiceDao.getServicesByDirtType( idFclt,  dirtTypeCode);
    }

    @Override
    public List<CleanService> getCleanServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup) {
        return cleanServiceDao.getCleanServiceByServiceGroup( idFclt,  serviceGroup);
    }

    @Override
    public List<CleanService> getCleanServiceByServiceGroup(int idFclt, String groupCode) {
        return cleanServiceDao.getCleanServiceByServiceGroup( idFclt,  groupCode);
    }

    @Override
    public CleanService getCleanServiceByGroupAndDirtType(int idFclt, int idGroup, int idDirtType) {
        return cleanServiceDao.getCleanServiceByGroupAndDirtType( idFclt,  idGroup,  idDirtType);
    }

    @Override
    public CleanService geCleanServiceByGroupAndDirtType(int idFclt, int idGroup, String dirtTypeCode) {
        return cleanServiceDao.geCleanServiceByGroupAndDirtType( idFclt,  idGroup,  dirtTypeCode);
    }
}
