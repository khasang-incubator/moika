package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("washServiceDao")
public class WashServiceDaoImpl extends BaseMoikaConcreatServiceDaoImpl<WashService> implements WashServiceDao {
    @Autowired
    protected DataAccessUtil dataAccessUtil;
    
    public WashServiceDaoImpl() {
    }

    public WashServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public WashService getWashServiceByIdAndType(long idService, int idCarType){
        final Session session = sessionFactory.getCurrentSession();
        Class entityClass = getDaoType();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq("id", idService));
        criteria.add(Restrictions.eq("idCarType", idCarType));
        WashService washService = (WashService)criteria.uniqueResult();
        return washService;       
    }

    @Override
    public WashService getWashServiceByIdAndType(long idService, String carTypeCode){
        final Session session = sessionFactory.getCurrentSession();
        Class entityClass = getDaoType();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq("id", idService));
        criteria.add(Restrictions.eq("carTypeEntity.code", carTypeCode));
        WashService washService = (WashService)criteria.uniqueResult();
        return washService;
    }
}
