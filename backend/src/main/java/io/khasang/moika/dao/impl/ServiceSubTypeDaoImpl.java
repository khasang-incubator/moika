package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceSubTypeDao;
import io.khasang.moika.entity.ServiceGroup;
import io.khasang.moika.entity.ServiceType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository("ServiceSubTypeDao")
public class ServiceSubTypeDaoImpl extends AllTypeDaoImpl<ServiceType> implements ServiceSubTypeDao {

    public ServiceSubTypeDaoImpl() {
    }

    @Override
    public List<ServiceGroup> getServiceSubTypeBybaseType(String typeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from service_subtypes where serviceType.code = "+ typeCode);
        return query.getResultList();
    }

    @Override
    public List<ServiceGroup> getServiceSubTypeBybaseType(int idType) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from service_subtypes where idBaseType = "+ idType);
        return query.getResultList();
    }
}
