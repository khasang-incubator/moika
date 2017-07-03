package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
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
    @JsonIgnore
    protected int id;
    @Id
    @Column(name = "date_x")
    @Temporal(value = TemporalType.DATE)
    @JsonIgnore
    protected Date dateX;
    @Id
    @Column(name = "time_on_starts")
    @JsonIgnore
    @JsonFormat(pattern = "HH:mm")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    @JsonIgnore
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeOnEnds;


    public WashFacilityTimeTable() {
    }

    public WashFacilityTimeTable(int idFacility, Date dateX, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idFacility;
        this.dateX = dateX;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
    }

    @JsonIgnore
    public int getIdFaciilty() {
        return id;
    }
    @JsonIgnore
    public void setIdFacility(int idFclt) {
        this.id = idFclt;
    }
    @JsonIgnore
    public Date getDateX() {
        return dateX;
    }
    @JsonIgnore
    public void setdateX(Date dateX) {
        this.dateX = dateX;
    }

    @JsonIgnore
    public LocalTime getTimeOnStarts() {
        return timeOnStarts;
    }

    public void setTimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    @JsonIgnore
    public LocalTime getTimeOnEnds() {
        return timeOnEnds;
    }

    public void setTimeOnEnds(LocalTime timeOnEnds) {
        this.timeOnEnds = timeOnEnds;
    }

    public WorkHours getWorkHours(){
        return new WorkHours(timeOnStarts,timeOnEnds);
    }

    public  void setWorkHours(WorkHours workHours){
        this.timeOnEnds = workHours.timeOnStarts;
        this.timeOnEnds = workHours.timeOnEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityTimeTable)) return false;

        WashFacilityTimeTable that = (WashFacilityTimeTable) o;

        if (id != that.getIdFaciilty()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        return this.timeOnStarts.equals(that.getWorkHours().getTimeOnStarts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdFaciilty(), getDateX(), timeOnStarts );
    }
}
