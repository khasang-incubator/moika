package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    protected int id;
    @Id
    @Column(name = "day_of_week")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    protected DayOfWeek weekDay;
    @Id
    @JsonIgnore
    @Column(name = "time_on_starts")
    @JsonFormat(pattern = "HH:mm")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    @JsonFormat(pattern = "HH:mm")
   // @Temporal(TemporalType.TIME)
    @JsonIgnore
    protected LocalTime timeOnEnds;

    public WashBoxWeekTimeTable() {
    }

    public WashBoxWeekTimeTable(int idBox, DayOfWeek weekDay, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idBox;
        this.weekDay = weekDay;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
    }
    @JsonIgnore
    public int getIdBox() {
        return id;
    }
    @JsonIgnore
    public void setIdBox(int idBox) {
        this.id = idBox;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

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
