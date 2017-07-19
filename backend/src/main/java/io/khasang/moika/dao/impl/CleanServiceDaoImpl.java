package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CleanServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.entity.EServiceGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository("cleanServiceDao")
public class CleanServiceDaoImpl extends AMoikaServiceDaoImpl<CleanService> implements CleanServiceDao {

    public CleanServiceDaoImpl() {
    }

    public CleanServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CleanService> getCleanServices(int idFclt) throws MoikaDaoException {
        return this.getServicesByType(idFclt, "CLEAN");
    }

    @Override
    public List<CleanService> getServicesByDirtType(int idFclt, String dirtTypeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from clean_Services where idFacility = :idFacility and dirtTypeEntity.code = :dirtTypeCode", CleanService.class);
        query.setParameter("idFacility", idFclt);
        query.setParameter("dirtTypeCode", dirtTypeCode);
        List<CleanService> services = query.getResultList();
        return services;
    }

    @Override
    public List<CleanService> getCleanServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup) {
        return this.getServicesByGroup(idFclt, serviceGroup );
    }

    @Override
    public List<CleanService> getCleanServiceByServiceGroup(int idFclt, String groupCode) {
        return this.getServicesByGroup(idFclt, groupCode );
    }

    @Override
    public CleanService getCleanServiceByGroupAndDirtType(int idFclt, int idGroup, int idDirtType) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from clean_Services where idFacility = :idFclt and idGroup = :idGroup and  idDirtType = :idDirtType", CleanService.class );
        query.setParameter("idFclt", idFclt);
        query.setParameter("idGroup", idGroup);
        query.setParameter("idDirtType", idDirtType);
        CleanService service = (CleanService) query.getSingleResult();
        return service;
    }

    @Override
    public CleanService geCleanServiceByGroupAndDirtType(int idFclt, int idGroup, String dirtTypeCode) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from clean_Services where idFacility = :idFclt and idGroup = :idGroup and  dirtTypeEntity.code = :dirtTypeCode", CleanService.class );
        query.setParameter("idFclt", idFclt);
        query.setParameter("idGroup", idGroup);
        query.setParameter("dirtTypeCode", dirtTypeCode);
        CleanService service = (CleanService) query.getSingleResult();
        return service;
    }
}
