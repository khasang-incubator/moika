package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.WashBox;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washBoxDao")
public class WashBoxDaoImpl extends MoikaDaoCrudImpl<WashBox> implements WashBoxDao{


    @Override
    public WashBox getWashBoxByName(int idFacility, String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashBox.class);
        criteria.add(Restrictions.eq("washFacility", idFacility));
        criteria.add(Restrictions.eq("boxName", name));
        return (WashBox) criteria.uniqueResult();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility) {
       // Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_fclt = ?;")
      //          .create(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, idFacility);
     //   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesByTypeId(int boxType) {
     //   Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_type = ?;")
     //           .create(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idType = ?");
        query.setParameter(0, boxType);
      //  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesByStatusId(int boxStatus) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idType = ?");
        query.setParameter(0, boxStatus);
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesByTypeCode(String boxType) {
        //   Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_type = ?;")
        //           .create(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where boxTypeEntity.code = ?");
        query.setParameter(0, boxType);
        //  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesByStatusCode(String boxStatus) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where boxStatusEntity.code = ?");
        query.setParameter(0, boxStatus);
        return query.list();
    }

}
