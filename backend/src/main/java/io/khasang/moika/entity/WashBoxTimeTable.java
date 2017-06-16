package io.khasang.moika.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 *  WashBoxTimeTable - Сущность описывающая расписание работы(нерабочие часы) конкретного бокса на автомойке,
 *  котрое может отличаться от общего расписания работы автомойки
 *  В бизнес логике должно проверяться что оно не выходить за пределы расписания автомойки
 */
@Entity(name = "box_time_table")
@IdClass(TimeTablePk.class)
public class WashBoxTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box")
    protected int id;
    @Id
    @Column(name = "date_x")
    protected Date dateX;
    @Id
    @Column(name = "time_off_starts")
    @Temporal(TemporalType.TIME)
    protected Date timeOffStarts;

    @Column(name = "time_off_ends")
    @Temporal(TemporalType.TIME)
    protected Date timeOffEnds;

    public WashBoxTimeTable() {
    }

    public WashBoxTimeTable(int idBox, Date dateX, Date timeOpen ) {
        this.id = idBox;
        this.dateX = dateX;
        if (DateUtils.isSameDay(this.dateX, timeOpen)) {
            this.timeOffStarts = timeOpen;
            this.timeOffEnds = DateUtils.round(this.timeOffStarts, Calendar.DATE);
        }
    }

    public int getIdBox() {
        return id;
    }

    public void setIdBox(int idBox) {
        this.id = idBox;
    }

    public Date getDateX() {
        return dateX;
    }

    public void setDateX(Date dateX) {
        this.dateX = dateX;
    }

    public Date getTimeOffStarts() {
        return timeOffStarts;
    }

    public void setTimeOffStarts(Date timeOffStarts) {
        this.timeOffStarts = timeOffStarts;
    }

    public Date getTimeOffEnds() {
        return timeOffEnds;
    }

    public void setTimeOffEnds(Date timeOffEnds) {
        this.timeOffEnds = timeOffEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashBoxTimeTable)) return false;

        WashBoxTimeTable that = (WashBoxTimeTable) o;

        if (getIdBox() != that.getIdBox()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        if (!getTimeOffStarts().equals(that.getTimeOffStarts())) return false;
        return getTimeOffEnds().equals(that.getTimeOffEnds());
    }

    @Override
    public int hashCode() {
        int result = getIdBox();
        result = 31 * result + getDateX().hashCode();
        result = 31 * result + getTimeOffStarts().hashCode();
        return result;
    }
}
