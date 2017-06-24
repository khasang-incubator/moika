package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashFacilityCalendarDao;
import io.khasang.moika.entity.WashFacilityCalendar;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class WashFacilityCalendarDaoImpl extends MoikaDaoCrudImpl<WashFacilityCalendar> implements WashFacilityCalendarDao {
    private SessionFactory sessionFactory;

    public WashFacilityCalendarDaoImpl() {
    }

    @Override
    public List<WashFacilityCalendar> getFacilityWorkCalendar(int idFacility) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from facility_calendar where idFclt = ?");
        query.setParameter(0, idFacility);
        //   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
}
