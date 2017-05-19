package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("clientDao")
public class ClientDaoImpl extends MoikaDaoCrudImpl<Client> implements ClientDao {

    public ClientDaoImpl() {
    }

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Client> getClientsByStatus(int idStatus) {
    Query query  = sessionFactory.getCurrentSession().createQuery("from clients where status = ?");
        query.setParameter(0, idStatus);
        return query.list();
    }

    @Override
    public List<Client> getClientsByStatus(String statusCode) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from clients c join client_status s " +
                "on c.idStatus = s.id where s.code = ?");
        query.setParameter(0, statusCode);
        return query.list();
    }

    @Override
    public List<Client> getClientsOnFacility(int idFclt) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from clients c join orders o " +
                "on c.id = o.idClient where  o.idFclt = ?");
        query.setParameter(0, idFclt);
        return query.list();
    }


    @Override
    public List<Client> getClientsByCarNum(String carNumber) {
        //Query query  = sessionFactory.getCurrentSession().createQuery("from clients cl join cars c where c.carNumber= ?");
        Query query  = sessionFactory.getCurrentSession().createQuery("from cars where carNumber= ?");
        query.setParameter(0, carNumber);
        Car car = (Car)query.getSingleResult();
        return car.getClients();
    }
}
