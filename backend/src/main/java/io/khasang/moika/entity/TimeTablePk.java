package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class TimeTablePk implements Serializable{

    @Column(name = "idfclt")
    protected int idFclt;
    @Column(name = "date_x")
    protected Date dateX;
    @Column(name = "time_off_starts")
    @Temporal(TemporalType.TIME)
    protected Date timeOffStarts;


    public TimeTablePk() {
    }

    public TimeTablePk(int idFclt, Date dateX, Date timeOffStarts) {
        this.idFclt = idFclt;
        this.dateX = dateX;
        this.timeOffStarts = timeOffStarts;
    }

    public int getIdFaciilty() {
        return idFclt;
    }

    public void setIdFacility(int idFclt) {
        this.idFclt = idFclt;
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
        return idFclt == that.idFclt &&
                Objects.equals(dateX, that.dateX) &&
                Objects.equals(timeOffStarts, that.timeOffStarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, dateX, timeOffStarts);
    }
}
