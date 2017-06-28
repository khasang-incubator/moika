package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
/**
 * Сужность составного первичного ключа для рабочего календаря
 */
@Embeddable
public class FacilityCalendarPk extends ABaseMoikaEntity{

    @Column(name = "id_fclt")
    protected int idFclt;
    @Column(name = "date_x")
    @Temporal(value = TemporalType.DATE)
    protected Date dateX;


    public FacilityCalendarPk() {
    }

    public FacilityCalendarPk(int idFclt, Date dateX) {
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

    public void setDateX(Date dateX) {
        this.dateX = dateX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityCalendarPk that = (FacilityCalendarPk) o;
        return idFclt == that.idFclt &&
                Objects.equals(dateX, that.dateX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, dateX);
    }
}
