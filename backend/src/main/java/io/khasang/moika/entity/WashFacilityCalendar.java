package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 *  WashBoxTimeTable - Сущность описывающая расписание работы(нерабочие часы)  автомойки
 *
 */
@Entity(name = "facility_calendar")
public class WashFacilityCalendar extends ABaseMoikaEntity {

    @EmbeddedId
    private CalendarPk fcltCalendarId;

    @Column(name = "id_date_type", insertable=false, updatable=false)
    private int idDateType;
    @ManyToOne
    @JoinColumn(name = "id_date_type")
    @JsonBackReference
    private CalendarDateType dateType;



}
