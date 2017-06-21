package io.khasang.moika.entity;

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
public class WashFacilityWeekTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    protected int id;
    @Id
    @Column(name = "day_of_week")
    protected DayOfWeek weekDay;
    @Id
    @Column(name = "time_off_starts")
    @Temporal(TemporalType.TIME)
    protected LocalTime timeOffStarts;

    @Column(name = "time_off_ends")
    @Temporal(TemporalType.TIME)
    protected LocalTime timeOffEnds;

    public WashFacilityWeekTimeTable() {
    }

    public WashFacilityWeekTimeTable(int idFclt, DayOfWeek weekDay, LocalTime timeOffStarts, LocalTime timeOffEnds) {
        this.id = idFclt;
        this.weekDay = weekDay;
        this.timeOffStarts = timeOffStarts;
        this.timeOffEnds = timeOffEnds;
    }

    public int getIdFclt() {
        return id;
    }

    public void setIdFclt(int idFclt) {
        this.id = idFclt;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
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
        if (!(o instanceof WashFacilityWeekTimeTable)) return false;
        WashFacilityWeekTimeTable that = (WashFacilityWeekTimeTable) o;
        return id == that.id &&
                getWeekDay() == that.getWeekDay() &&
                getTimeOffStarts().equals(that.getTimeOffStarts()) &&
                getTimeOffEnds().equals(that.getTimeOffEnds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getWeekDay(), getTimeOffStarts());
    }
}
