package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TimeTablePk implements Serializable{

    @Column(name = "idfclt")
    protected int idFclt;
    @Column(name = "date_x")
    protected Date dateX;
    @Column(name = "time_open")
    @Temporal(TemporalType.TIME)
    protected Date timeOpen;


    public TimeTablePk() {
    }

    public TimeTablePk(int idFclt, Date dateX, Date timeOpen) {
        this.idFclt = idFclt;
        this.dateX = dateX;
        this.timeOpen = timeOpen;
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

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTablePk that = (TimeTablePk) o;
        return idFclt == that.idFclt &&
                Objects.equals(dateX, that.dateX) &&
                Objects.equals(timeOpen, that.timeOpen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, dateX, timeOpen);
    }
}
