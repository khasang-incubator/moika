package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashFacilityWeekTimeTableDao;
import io.khasang.moika.entity.WashFacilityWeekTimeTable;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WashFacilityWeekTimeTableDaoImpl extends MoikaDaoCrudImpl<WashFacilityWeekTimeTable> implements WashFacilityWeekTimeTableDao {
    private SessionFactory sessionFactory;

    public WashFacilityWeekTimeTableDaoImpl() {
    }


}
