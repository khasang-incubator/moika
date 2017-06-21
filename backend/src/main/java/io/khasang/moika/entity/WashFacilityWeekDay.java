package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

/**
 *  WashFacilityWeekDay - Сущность описывающая расписание работы(нерабочие недели)  автомойки
 *
 */
@Entity(name = "facility_week_days")
public class WashFacilityWeekDay extends ABaseMoikaEntity {

    @EmbeddedId
    private FacilityWeekdayPk facilityWeekdayPk;

    @Column(name = "id_day_type", insertable=false, updatable=false)
    private int idDateType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_day_type")
    private CalendarDateType dateType;


    public WashFacilityWeekDay() {
    }

    public WashFacilityWeekDay(FacilityWeekdayPk facilityWeekdayPk, int idDateType, CalendarDateType dateType) {
        this.facilityWeekdayPk = facilityWeekdayPk;
        this.idDateType = idDateType;
        this.dateType = dateType;
    }


    public void setIdFacility(int idFclt) {
        this.facilityWeekdayPk.setIdFacility(idFclt);
    }

    public DayOfWeek getWeekDay() {
        return facilityWeekdayPk.getWeekDay();
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.facilityWeekdayPk.setWeekDay(weekDay);
    }

    public void setWeekDay(int weekDay) {
        this.facilityWeekdayPk.setWeekDay(weekDay);
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
        if (!(o instanceof WashFacilityWeekDay)) return false;

        WashFacilityWeekDay that = (WashFacilityWeekDay) o;

        if (getIdDateType() != that.getIdDateType()) return false;
        if (!facilityWeekdayPk.equals(that.facilityWeekdayPk)) return false;
        return dateType.equals(that.getDateType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityWeekdayPk, dateType);
    }
}
