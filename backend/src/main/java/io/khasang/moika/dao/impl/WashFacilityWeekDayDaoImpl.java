package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashFacilityWeekDayDao;
import io.khasang.moika.entity.WashFacilityWeekDay;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WashFacilityWeekDayDaoImpl extends MoikaDaoCrudImpl<WashFacilityWeekDay> implements WashFacilityWeekDayDao {

    public WashFacilityWeekDayDaoImpl() {
    }


}
