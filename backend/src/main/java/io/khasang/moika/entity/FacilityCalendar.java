package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "facility_calendar")
public class FacilityCalendar extends ABaseMoikaEntity {

    @Id
    private CalendarPk pk;

    @Column(name = "id_date_type", insertable=false, updatable=false)
    private int idDateType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn( name = "id_date_type", foreignKey = @ForeignKey(name = "fk_calendar_types") )
    private CalendarDateType dateTypeEntity;



    public FacilityCalendar() {
    }

    public FacilityCalendar(int idFacility, Date dateX, CalendarDateType dateTypeEntity ) {
        this.pk.idFclt = idFacility;
        this.pk.dateX = dateX;
        this.dateTypeEntity = dateTypeEntity;
    }

    public CalendarPk getPk() {
        return pk;
    }

    public int getIdDateType() {
        return idDateType;
    }

    public CalendarDateType getDateTypeEntity() {
        return dateTypeEntity;
    }

    public void setIdDateType(int idDateType) {
        this.idDateType = idDateType;
    }

    public void setDateTypeEntity(CalendarDateType dateTypeEntity) {
        this.dateTypeEntity = dateTypeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityCalendar that = (FacilityCalendar) o;
        return idDateType == that.idDateType &&
                Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, idDateType);
    }
}
