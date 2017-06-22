package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 *  WashАфсшдшенTimeTable - Сущность описывающая расписание работы(нерабочие часы)  автомойки
 *  Сущность, описывающая рабочий календарь (выходнные или нестандартные дни) для автомойки, отличные от регулярнрых рабочих недель,
 *  реализуемых в WashFacilityWeekDay
 *
 */
@Entity(name = "facility_calendar")
public class WashFacilityCalendar extends ABaseMoikaEntity {

    @EmbeddedId
    private FacilityCalendarPk fcltCalendarPk;

    @Column(name = "id_date_type", insertable=false, updatable=false)
    private int idDateType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_date_type", foreignKey = @ForeignKey(name = "fk_calendar_types") )
    private CalendarDateType dateType;


    public WashFacilityCalendar() {
    }

    public WashFacilityCalendar(int idFacility, Date dateX, CalendarDateType dateType ) {
        this.fcltCalendarPk.idFclt = idFacility;
        this.fcltCalendarPk.dateX = dateX;
        this.dateType = dateType;
    }

    public FacilityCalendarPk getFcltCalendarId() {
        return fcltCalendarPk;
    }

    public Date getCalendarDate() {
        return fcltCalendarPk.getDateX();
    }

    public void setCalendarDate(Date dateX) {
        this.fcltCalendarPk.setDateX(dateX);
    }

    public int getIdDateType() {
        return idDateType;
    }

    public void setIdDateType(int idDateType) {
        this.idDateType = idDateType;
    }

    public CalendarDateType getDateType() {
        return dateType;
    }

    public void setDateType(CalendarDateType dateType) {
        this.dateType = dateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityCalendar)) return false;
        WashFacilityCalendar that = (WashFacilityCalendar) o;
        return Objects.equals(fcltCalendarPk, that.fcltCalendarPk) &&
                Objects.equals(getDateType(), that.getDateType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fcltCalendarPk, getDateType());
    }
}
