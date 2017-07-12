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
    public List<T> getServicesByStatus(int idStatus) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idStatus="+ idStatus);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByStatus(String statusCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from services s join service_status t on s.idStatus = t.id where t.code="+ statusCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByType(String typeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where serviceTypeCode="+ typeCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getActualServices() throws MoikaDaoException {
        return getServicesByStatus(0);
    }

    @Override
    public List<T> getServicesBySubType(String subTypeCode) throws MoikaDaoException{
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where serviceSubTypeEntity.code ="+ subTypeCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesByCarType(String carTypeCode) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("select s from services s inner join car_types c on  s.idCarType = c.id " +
                " where c.code = ?");
        query.setParameter(0, carTypeCode);
        List<T> services = query.getResultList();
        return services;
    }

    @Override
    public List<T> getServicesOnFacility(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        org.hibernate.query.Query query  = session.createQuery("from services s  where s.idFacility = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }

    @Override
    public List<T> getActualServicesOnFacility(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("select s from services s inner join service_status ss on s.idStatus = ss.id "+
                "where ss.code = 'ON' and s.idFacility = ?");
        query.setParameter(0, idFclt);
        List<T> services = query.getResultList();
        return services;
    }
}
