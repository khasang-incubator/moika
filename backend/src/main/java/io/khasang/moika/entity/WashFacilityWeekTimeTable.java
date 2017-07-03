package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 * WashBoxTimeTable - Сущность описывающая расписание работы(нерабочие часы) конкретного бокса на автомойке,
 * котрое может отличаться от общего расписания работы автомойки
 * В бизнес логике должно проверяться что оно не выходить за пределы расписания автомойки
 */
@Entity(name = "facility_week_time_table")
@IdClass(WeekTimeTablePk.class)
public class WashFacilityWeekTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    @JsonIgnore
    protected int id;
    @Id
    @Column(name = "day_of_week")
    @JsonIgnore
    protected DayOfWeek weekDay;
    @Id
    @Column(name = "time_on_starts")
    @JsonIgnore
    private LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    @JsonIgnore
    private LocalTime timeOnEnds;

    public WashFacilityWeekTimeTable() {
    }

    public WashFacilityWeekTimeTable(int idFclt, DayOfWeek weekDay, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idFclt;
        this.weekDay = weekDay;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
    }
    @JsonIgnore
    public int getIdFclt() {
        return id;
    }
    @JsonIgnore
    public void setIdFclt(int idFclt) {
        this.id = idFclt;
    }
    @JsonIgnore
    public DayOfWeek getWeekDay() {
        return weekDay;
    }
    @JsonIgnore
    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    @JsonIgnore
    public LocalTime getTimeOnStarts() {
        return timeOnStarts;
    }
    @JsonIgnore
    public void setTimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    @JsonIgnore
    public LocalTime getTimeOnEnds() {
        return timeOnEnds;
    }
    @JsonIgnore
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
        if (!(o instanceof WashFacilityWeekTimeTable)) return false;
        WashFacilityWeekTimeTable that = (WashFacilityWeekTimeTable) o;
        return id == that.id &&
                getWeekDay() == that.getWeekDay() &&
                this.timeOnStarts.equals(that.getWorkHours().getTimeOnStarts()) &&
                this.timeOnEnds.equals(that.getWorkHours().getTimeOnEnds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getWeekDay(), timeOnStarts);
    }


}
