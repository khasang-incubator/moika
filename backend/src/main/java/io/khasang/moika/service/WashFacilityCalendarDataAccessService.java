package io.khasang.moika.service;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashFacilityCalendar;

import java.util.List;

/**
 * Created by pauls on 25.05.2017.
 */
public interface WashFacilityCalendarDataAccessService {
    List<WashFacilityCalendar> getFacilityWorkCalendar(int idFacility);
}
