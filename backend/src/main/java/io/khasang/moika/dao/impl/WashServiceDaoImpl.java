package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
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
    public WashService getWashServiceByIdAndCarType(long idService, int idCarType){
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from wash_services where id = :idService and idCarType = : idCarType");
        query.setParameter("idService", idService);
        query.setParameter("idCarType", idCarType);
        WashService washService = (WashService) query.getSingleResult();
        return washService;       
    }

    @Override
    public WashService getWashServiceByIdAndCarType(long idService, String carTypeCode){
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from wash_services where id = :idService and carTypeEntity.code = :carTypeCode");
        query.setParameter("idService", idService);
        query.setParameter("carTypeCode", carTypeCode);
        WashService washService = (WashService) query.getSingleResult();
        return washService;
    }

    @Override
    public List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idFacility = :idFacility and car_types.code = :carTypeCode", WashService.class);
        query.setParameter("idFacility", idFclt);
        query.setParameter("carTypeCode", carTypeCode);
        List<WashService> services = query.getResultList();
        return services;
    }
}
