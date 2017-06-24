package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashFacilityTimeTableDao;
import io.khasang.moika.dao.WashFacilityWeekDayDao;
import io.khasang.moika.entity.WashFacilityTimeTable;
import io.khasang.moika.entity.WashFacilityWeekDay;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WashFacilityTimeTableDaoImpl extends MoikaDaoCrudImpl<WashFacilityTimeTable> implements WashFacilityTimeTableDao {
    private SessionFactory sessionFactory;

    public WashFacilityTimeTableDaoImpl() {
    }


}
