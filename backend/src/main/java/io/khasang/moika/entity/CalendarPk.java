package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class CalendarPk extends ABaseMoikaEntity{

    @Column(name = "idfclt")
    protected int idFclt;
    @Column(name = "date_x")
    protected Date dateX;



    public CalendarPk() {
    }

    public CalendarPk(int idFclt, Date dateX) {
        this.idFclt = idFclt;
        this.dateX = dateX;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarPk that = (CalendarPk) o;
        return idFclt == that.idFclt &&
                Objects.equals(dateX, that.dateX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, dateX);
    }
}
