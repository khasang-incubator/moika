package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.entity.WashAddr;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washAddrDao")
public class WashAddrDaoImpl extends MoikaDaoCrudImpl<WashAddr> implements WashAddrDao {

    public WashAddrDaoImpl() {
    }


    public WashAddrDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<WashAddr> getWashAddrListInCity(int cityId){
        Query query  = sessionFactory.getCurrentSession().createQuery("from WashAddr a inner join cities c on a.idCity = c.id where c.id = ?");
        query.setParameter(0, cityId);
        return query.list();
    }
}
