package io.khasang.moika.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Сужность составного первичного ключа для расписаний
 */
@Embeddable
public class TimeTablePk implements Serializable{

    protected int id;
    @Temporal(TemporalType.DATE)
    protected Date dateX;
    @Temporal(TemporalType.TIME)
    protected Date timeOffStarts;


    public TimeTablePk() {
    }

    public TimeTablePk(int id, Date dateX, Date timeOffStarts) {
        this.id = id;
        this.dateX = dateX;
        this.timeOffStarts = timeOffStarts;
    }

    public int getIdFaciilty() {
        return id;
    }

    public void setIdFacility(int id) {
        this.id = id;
    }

    public Date getDateX() {
        return dateX;
    }

    public void setdateX(Date dateX) {
        this.dateX = dateX;
    }

    public Date getTimeOffStarts() {
        return timeOffStarts;
    }

    public void settimeOffStarts(Date timeOffStarts) {
        this.timeOffStarts = timeOffStarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTablePk that = (TimeTablePk) o;
        return id == that.id &&
                Objects.equals(dateX, that.dateX) &&
                Objects.equals(timeOffStarts, that.timeOffStarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateX, timeOffStarts);
    }
}
