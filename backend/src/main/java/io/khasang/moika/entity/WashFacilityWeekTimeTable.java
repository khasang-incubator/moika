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
@IdClass(WeekTimeTablePk.class)
public class WashFacilityWeekTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    protected int id;
    @Id
    @Column(name = "day_of_week")
    protected DayOfWeek weekDay;
    @Id
    @Column(name = "time_on_starts")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    protected LocalTime timeOnEnds;

    public WashFacilityWeekTimeTable() {
    }

    public WashFacilityWeekTimeTable(int idFclt, DayOfWeek weekDay, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idFclt;
        this.weekDay = weekDay;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
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

    public LocalTime getTimeOnStarts() {
        return timeOnStarts;
    }

    public void setTimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    public LocalTime getTimeOnEnds() {
        return timeOnEnds;
    }

    public void setTimeOnEnds(LocalTime timeOnEnds) {
        this.timeOnEnds = timeOnEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityWeekTimeTable)) return false;
        WashFacilityWeekTimeTable that = (WashFacilityWeekTimeTable) o;
        return id == that.id &&
                getWeekDay() == that.getWeekDay() &&
                getTimeOnStarts().equals(that.getTimeOnStarts()) &&
                getTimeOnEnds().equals(that.getTimeOnEnds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getWeekDay(), getTimeOnStarts());
    }
}
