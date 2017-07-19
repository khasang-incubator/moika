package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository("washServiceDao")
public class WashServiceDaoImpl extends AMoikaServiceDaoImpl<WashService> implements WashServiceDao {
        @Autowired
    protected DataAccessUtil dataAccessUtil;
    
    public WashServiceDaoImpl() {
    }

    public WashServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<WashService> getWashServices(int idFclt) throws MoikaDaoException {
        return this.getServicesByType(idFclt, "WASH");
    }

    @Override
    public List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from wash_services where idFacility = :idFacility and carTypeEntity.code = :carTypeCode", WashService.class);
        query.setParameter("idFacility", idFclt);
        query.setParameter("carTypeCode", carTypeCode);
        List<WashService> services = query.getResultList();
        return services;
    }

    @Override
    public List<WashService> getWashServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup) {
        return this.getServicesByGroup(idFclt, serviceGroup );
    }

    @Override
    public List<WashService> getWashServiceByServiceGroup(int idFclt, String groupCode) {
        return this.getServicesByGroup(idFclt, groupCode );
    }

    @Override
    public WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, int idCarType) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from wash_services where idFacility = :idFclt and idGroup = :idGroup and  idCarType = :idCarType", WashService.class );
        query.setParameter("idFclt", idFclt);
        query.setParameter("idGroup", idGroup);
        query.setParameter("idCarType", idCarType);
        WashService service = (WashService) query.getSingleResult();
        return service;
    }

    @Override
    public WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, String carTypeCode) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from wash_services where idFacility = :idFclt and idGroup = :idGroup and carTypeEntity.code = :carTypeCode", WashService.class );
        query.setParameter("idFclt", idFclt);
        query.setParameter("idGroup", idGroup);
        query.setParameter("carTypeCode", carTypeCode);
        WashService service = (WashService) query.getSingleResult();
        return service;
    }
}
