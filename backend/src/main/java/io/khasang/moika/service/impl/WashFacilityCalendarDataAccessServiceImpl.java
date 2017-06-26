package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CityDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.dao.WashFacilityCalendarDao;
import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashFacilityCalendar;
import io.khasang.moika.service.WashAddrDataAccessService;
import io.khasang.moika.service.WashFacilityCalendarDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("WashFacilityCalendarDataAccessService")
@Transactional
public class WashFacilityCalendarDataAccessServiceImpl implements WashFacilityCalendarDataAccessService {
    @Autowired
    private WashFacilityCalendarDao washFacilityCalendarDao;


    @Override
    public List<WashFacilityCalendar> getFacilityWorkCalendar(int idFacility) {
        return washFacilityCalendarDao.getFacilityWorkCalendar(idFacility);
    }
}
