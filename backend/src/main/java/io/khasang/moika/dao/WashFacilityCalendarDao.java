package io.khasang.moika.dao;


import io.khasang.moika.entity.WashFacilityCalendar;

import java.util.List;

public interface WashFacilityCalendarDao extends IMoikaDaoCrud<WashFacilityCalendar>   {
        List<WashFacilityCalendar> getFacilityWorkCalendar(int idFacility);
}
