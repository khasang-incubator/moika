package io.khasang.moika.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Сужность составного первичного ключа для расписаний
 */
@Embeddable
public class WeekTimeTablePk implements Serializable{

    protected int id;
    protected DayOfWeek weekDay;;
    protected LocalTime timeOnStarts;

    public WeekTimeTablePk() {
    }

    public WeekTimeTablePk(int id, DayOfWeek weekDay, LocalTime timeOnStarts) {
        this.id = id;
        this.weekDay = weekDay;
        this.timeOnStarts = timeOnStarts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeekTimeTablePk that = (WeekTimeTablePk) o;
        return id == that.id &&
                Objects.equals(weekDay, that.weekDay) &&
                Objects.equals(timeOnStarts, that.timeOnStarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weekDay, timeOnStarts);
    }
}
