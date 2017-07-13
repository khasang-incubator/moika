package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;


public abstract class AMoikaServiceDaoImpl<T extends MoikaService> extends MoikaDaoCrudImpl<T> implements MoikaServiceDao<T> {

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<T> getServicesByStatus(int idFclt, int idStatus) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idFacility = :idFacility and idStatus= :idStatus");
        query.setParameter("idFacility", idFclt);
        query.setParameter("idStatus", idStatus);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByStatus(int idFclt, String statusCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from services s join service_status t on s.idStatus = t.id "+
                " where s.idFacility = :idFacility and t.code= :statusCode");
        query.setParameter("idFacility", idFclt);
        query.setParameter("statusCode", statusCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByType(int idFclt, String typeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services s inner join  service_group g on s.idGroup = g.id "+
                " inner join  service_types t on s.idGroup = g.id "+
                " where s.idFacility = :idFacility and t.code = :typeCode", MoikaService.class);
        query.setParameter("idFacility", idFclt);
        query.setParameter("typeCode", typeCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getActualServices(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("select s from services s inner join service_status ss on s.idStatus = ss.id "+
                "where ss.code = 'ON' and s.idFacility = ?", MoikaService.class);
        query.setParameter(0, idFclt);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByGroup(int idFclt, String groupCode) throws MoikaDaoException{
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idFacility = :idFacility and serviceGroupEntity.code = :groupCode");
        query.setParameter("idFacility", idFclt);
        query.setParameter("groupCode", groupCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServices(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        org.hibernate.query.Query query  = session.createQuery("from services s  where s.idFacility = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }


}
