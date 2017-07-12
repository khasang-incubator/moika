package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("moikaServiceDao")
public class MoikaServiceDaoImpl extends AMoikaServiceDaoImpl<MoikaService>  implements MoikaServiceDao<MoikaService> {


    public MoikaServiceDaoImpl() {
    }

    public MoikaServiceDaoImpl(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MoikaService> getServicesByCarType(String carTypeCode) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from services s inner join car_types c on  s.idCarType = c.id " +
                " where c.code = ?");
        query.setParameter(0, carTypeCode);
        return query.list();
    }

    @Override
    public List<MoikaService> getServicesOnFacility(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from services s  where s.idFacility = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }

    @Override
    public List<MoikaService> getActualServicesOnFacility(int idFclt) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from services s  inner join service_status ss on s.idStatus = ss.id "+
                "where ss.code = 'ON' and s.idFacility = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }
}
