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
@Entity(name = "box_week_time_table")
@IdClass(WeekTimeTablePk.class)
public class WashBoxWeekTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box")
    protected int id;
    @Id
    @Column(name = "day_of_week")
    protected DayOfWeek weekDay;
    @Id
    @Column(name = "time_on_starts")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
   // @Temporal(TemporalType.TIME)
    protected LocalTime timeOnEnds;

    public WashBoxWeekTimeTable() {
    }

    public WashBoxWeekTimeTable(int idBox, DayOfWeek weekDay, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idBox;
        this.weekDay = weekDay;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
    }

    public int getIdBox() {
        return id;
    }

    public void setIdBox(int idBox) {
        this.id = idBox;
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
        if (!(o instanceof WashBoxWeekTimeTable)) return false;
        WashBoxWeekTimeTable that = (WashBoxWeekTimeTable) o;
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
