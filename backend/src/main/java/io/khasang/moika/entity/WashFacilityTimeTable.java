package io.khasang.moika.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * FacilityTimeTable - Сущность описывающая расписание работы(нерабочие часы) конкретного автомойки,
 */
@Entity(name = "facility_time_table")
@IdClass(TimeTablePk.class)
public class WashFacilityTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    protected int id;
    @Id
    @Column(name = "date_x")
    protected Date dateX;
    @Id
    @Column(name = "time_off_starts")
    @Temporal(TemporalType.TIME)
    protected LocalTime timeOffStarts;

    @Column(name = "time_off_ends")
    @Temporal(TemporalType.TIME)
    private LocalTime timeOffEnds;


    public WashFacilityTimeTable() {
    }

    public WashFacilityTimeTable(int idFacility, Date dateX, LocalTime timeOffStarts, LocalTime timeOffEnds) {
        this.id = idFacility;
        this.dateX = dateX;
        this.timeOffStarts = timeOffStarts;
        this.timeOffEnds = timeOffEnds;
    }


    public int getIdFaciilty() {
        return id;
    }

    public void setIdFacility(int idFclt) {
        this.id = idFclt;
    }

    public Date getDateX() {
        return dateX;
    }

    public void setdateX(Date dateX) {
        this.dateX = dateX;
    }

    public LocalTime getTimeOffStarts() {
        return timeOffStarts;
    }

    public void setTimeOffStarts(LocalTime timeOffStarts) {
        this.timeOffStarts = timeOffStarts;
    }

    public LocalTime getTimeOffEnds() {
        return timeOffEnds;
    }

    public void setTimeOffEnds(LocalTime timeOffEnds) {
        this.timeOffEnds = timeOffEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityTimeTable)) return false;

        WashFacilityTimeTable that = (WashFacilityTimeTable) o;

        if (id != that.getIdFaciilty()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        return getTimeOffStarts().equals(that.getTimeOffStarts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdFaciilty(), getDateX(), getTimeOffStarts() );
    }
}
