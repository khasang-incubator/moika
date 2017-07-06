package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * WashBoxTimeTable - Сущность описывающая расписание работы(нерабочие часы) конкретного бокса на автомойке,
 * котрое может отличаться от общего расписания работы автомойки
 * В бизнес логике должно проверяться что оно не выходить за пределы расписания автомойки
 */
@Entity(name = "box_time_table")
@IdClass(TimeTablePk.class)
public class WashBoxTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box")
    @JsonIgnore
    protected int id;
    @Id
    @Column(name = "date_x")
    @Temporal(value = TemporalType.DATE)
    protected Date dateX;
    @Id
    @Column(name = "time_on_starts")
    @JsonIgnore
  //  @JsonFormat(pattern = "HH:mm")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    @JsonIgnore
  //  @JsonFormat(pattern = "HH:mm")
    protected LocalTime timeOnEnds;

    public WashBoxTimeTable() {
    }

    public WashBoxTimeTable(int idBox, Date dateX, LocalTime timeOpen, LocalTime timeClose) {
        this.id = idBox;
        this.dateX = dateX;
        this.timeOnStarts = timeOpen;
        this.timeOnEnds = timeClose;

    }
    @JsonIgnore
    public int getIdBox() {
        return id;
    }
    @JsonIgnore
    public void setIdBox(int idBox) {
        this.id = idBox;
    }

    public Date getDateX() {
        return dateX;
    }

    public void setDateX(Date dateX) {
        this.dateX = dateX;
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
        if (!(o instanceof WashBoxTimeTable)) return false;
        WashBoxTimeTable that = (WashBoxTimeTable) o;
        if (getIdBox() != that.getIdBox()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        if (!this.timeOnStarts.equals(that.getWorkHours().getTimeOnStarts())) return false;
        return this.timeOnEnds.equals(that.getWorkHours().getTimeOnEnds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDateX(), timeOnStarts);
    }
}
