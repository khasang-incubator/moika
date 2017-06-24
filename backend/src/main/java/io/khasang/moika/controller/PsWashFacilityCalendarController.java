package io.khasang.moika.controller;

import io.khasang.moika.dao.WashFacilityCalendarDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacilityCalendar;
import io.khasang.moika.service.BoxStatusDataAccessService;
import io.khasang.moika.service.BoxTypesDataAccessService;
import io.khasang.moika.service.PskvorWashBoxDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * Контроллер для управления рабочим календарем автомоек
 *
 * @author Pauls
 */
@RestController
@RequestMapping(value = "/api/facilityCalendar",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public class PsWashFacilityCalendarController {

    @Autowired
    WashFacilityCalendarDao calendarDao;


    /**
     * Вывод информации о всех днях в календаре мойки
     *
     * @return
     */
    @RequestMapping(value = "/{idFclt}", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public Object getWashBoxList(@PathVariable(value = "idFclt") int idFclt) {
        List<WashFacilityCalendar> fcltCalendar = calendarDao.getFacilityWorkCalendar(idFclt);
        if ((fcltCalendar == null) || (fcltCalendar.isEmpty()))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        else
            return fcltCalendar;
    }
}
