package io.khasang.moika.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity(name = "facility_time_table")
@IdClass(TimeTablePk.class)
public class FacilityTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
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
    private Date timeOffEnds;


    public FacilityTimeTable() {
    }

    public FacilityTimeTable(int idFacility, Date dateX, Date timeOpen ) {
        this.id = idFacility;
        this.dateX = dateX;
        if (DateUtils.isSameDay(this.dateX, timeOpen)) {
            this.timeOffStarts = timeOpen;
            this.timeOffEnds = DateUtils.round(this.timeOffStarts, Calendar.DATE);
        }
    }


    public int getIdFaciilty() {
        return id;
    }

    public void setIdFacility(int idFclt) {
        this.id = idFclt;
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

    public Date getTimeOffEnds() {
        return timeOffEnds;
    }

    public void setTimeOffEnds(Date timeOffEnds) {
        this.timeOffEnds = timeOffEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacilityTimeTable)) return false;

        FacilityTimeTable that = (FacilityTimeTable) o;

        if (id != that.getIdFaciilty()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        return getTimeOffStarts().equals(that.getTimeOffStarts());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + getDateX().hashCode();
        result = 31 * result + getTimeOffStarts().hashCode();
        return result;
    }
}
