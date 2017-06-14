package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by pauls on 13.06.2017.
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
