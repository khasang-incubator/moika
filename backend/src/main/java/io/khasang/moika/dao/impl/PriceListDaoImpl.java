package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.PriceListDao;
import io.khasang.moika.entity.PriceList;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pauls on 19.07.2017.
 */
@Transactional
@Repository("PriceListDao")
public class PriceListDaoImpl implements PriceListDao {
    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public PriceList getPriceListByServiceType(int idFclt, int serviceType) throws MoikaDaoException {
        Query query  = sessionFactory.getCurrentSession().createQuery("from service_types st inner join services s on st.id = s.idType "
                +" where s.idFacility = :idFacility and st.id = :serviceType ", PriceList.class);
        query.setParameter("idFacility", idFclt);
        query.setParameter("serviceType", serviceType);
        PriceList res = (PriceList) query.getSingleResult();
        return res;
    }

    @Override
    public List<PriceList> getFullPriceList(int idFclt) throws MoikaDaoException {
        Query query  = sessionFactory.getCurrentSession().createQuery("from service_types st inner join services s on st.id = s.idType "
                +" where s.idFacility = :idFacility ", PriceList.class);
        query.setParameter("idFacility", idFclt);
        List<PriceList> res = query.getResultList();
        return res;
    }
}
