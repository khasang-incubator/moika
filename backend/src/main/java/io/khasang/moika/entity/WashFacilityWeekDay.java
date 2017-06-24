package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.*;

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
    @JoinColumn(name = "id_day_type", foreignKey = @ForeignKey(name = "facility_week_days_calendar_date_types_id_type_fk"))
    private CalendarDateType dateType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt"),
            @JoinColumn(name = "day_of_week", referencedColumnName = "day_of_week")})
    private Set<WashFacilityWeekTimeTable> fcltWeekTimeTable = new HashSet<>();


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

    public Set<WashFacilityWeekTimeTable> getFcltWeekTimeTable() {
        return fcltWeekTimeTable;
    }

    public void setFcltWeekTimeTable(Set<WashFacilityWeekTimeTable> fcltWeekTimeTable) {
        this.fcltWeekTimeTable = fcltWeekTimeTable;
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
