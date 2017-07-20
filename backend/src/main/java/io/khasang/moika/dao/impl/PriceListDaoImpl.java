package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.PriceListDao;
import io.khasang.moika.entity.*;
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
    private PriceList workPriceList;

    @Override
    public PriceList getPriceListByServiceType(int idFclt, EServiceType serviceType) throws MoikaDaoException {
        Query query  = sessionFactory.getCurrentSession().createQuery("from service_types st where st.id = :serviceType ");
        query.setParameter("serviceType", serviceType.ordinal());
        workPriceList = (PriceList) query.getSingleResult();
        if  (workPriceList != null) getServicesByType(idFclt, serviceType);
        return workPriceList;
    }

    @Override
    public List<PriceList> getFullPriceList(int idFclt) throws MoikaDaoException {
        Query query  = sessionFactory.getCurrentSession().createQuery("from service_types st inner join services s on st.id = s.idType "
                +" where s.idFacility = :idFacility ", PriceList.class);
        query.setParameter("idFacility", idFclt);
        List<PriceList> res = query.getResultList();
        for (PriceList pl : res ){
            workPriceList = pl;
            getServicesByType(idFclt, workPriceList.getServiceType());
        }
        return res;
    }

    private void getServicesByType(int idFclt, EServiceType serviceType){
        final String serviceQueryStr = "from "+serviceType.getServiceEntityName()+" s  where s.idFacility = :idFacility and s.idType = :serviceType ";
        Query query  = sessionFactory.getCurrentSession().createQuery(serviceQueryStr);
        query.setParameter("serviceType", serviceType.ordinal());
        query.setParameter("idFacility", idFclt);
        switch (serviceType) {
            case WASH: {
                List<WashService> resService =  query.getResultList();
                workPriceList.setWashServices(resService);
                break;
            }
            case CLEAN: {
                List<CleanService> resService =  query.getResultList();
                workPriceList.setCleanServices(resService);
                break;
            }
            case CHEM_CLEAN: {
                List<ChemCleanService> resService =  query.getResultList();
                workPriceList.setChemCleanServices(resService);
                break;
            }
            case POLISH: {
                List<PolishService> resService =  query.getResultList();
                workPriceList.setPolishServices(resService);
                break;
            }
            case OTHER: {
                List<OtherService> resService =  query.getResultList();
                workPriceList.setOtherServices(resService);
                break;
            }
            case COMPLEX: {
                List<ComplexService> resService =  query.getResultList();
                workPriceList.setComplexServices(resService);
                break;
            }
        }
    }
}
