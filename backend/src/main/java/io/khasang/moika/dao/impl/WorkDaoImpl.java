package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class WorkDaoImpl  extends MoikaDaoCrudImpl<Work> implements WorkDao{

    public WorkDaoImpl() {
    }
    @Autowired
    public WorkDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Work> getWorksByIdOrder(long idOrder) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from works where idOrder = :idOrder ");
        query.setParameter("idOrder", idOrder);
        return query.getResultList();
    }

    @Override
    public Work getWorkInBox(long idWashBox) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from works where idBox = :idWashBox and  timeStart <= now() and timeFinish = null ");
        query.setParameter("idWashBox", idWashBox);
        return (Work)query.getSingleResult();
    }

    @Override
    public Work getWorkInBox(long idWashBox, Date workDateAndTime) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from works where idBox = :idWashBox and  timeStart <= :workDateAndTime and timeFinish >= :workDateAndTime ");
        query.setParameter("idWashBox", idWashBox);
        query.setParameter("workDateAndTime", workDateAndTime);
        return (Work)query.getSingleResult();
    }

    @Override
    public List<Work> getWorksInFacility(int idFclt) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from works where washBox.idFacility = :idFclt and  timeStart <=  now() and timeFinish = null  ");
        query.setParameter("idFclt", idFclt);
        return query.getResultList();
    }

    @Override
    public List<Work> getWorksInFacility(int idFclt, Date workDateAndTime) {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from works where washBox.idFacility = :idFclt and  timeStart <= :workDateAndTime and timeFinish >= :workDateAndTime  ");
        query.setParameter("idFclt", idFclt);
        query.setParameter("workDateAndTime", workDateAndTime);
        return query.getResultList();
    }
}
