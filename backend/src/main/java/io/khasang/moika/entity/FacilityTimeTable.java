package io.khasang.moika.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity(name = "facility_time_table")
public class FacilityTimeTable extends ABaseMoikaEntity {

    @Id
    private TimeTablePk pk;

    @Column(name = "time_open")
    @Temporal(TemporalType.TIME)
    private Date timeClose;


    public FacilityTimeTable() {
    }

    public FacilityTimeTable(int idFacility, Date dateX, Date timeOpen ) {
        this.pk.idFclt = idFacility;
        this.pk.dateX = dateX;
        if (DateUtils.isSameDay(this.pk.dateX, timeOpen)) {
            this.pk.timeOpen = timeOpen;
            this.timeClose = DateUtils.round(this.pk.timeOpen, Calendar.DATE);
        }
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
        FacilityTimeTable that = (FacilityTimeTable) o;
        return idDateType == that.idDateType &&
                Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, idDateType);
    }
}
