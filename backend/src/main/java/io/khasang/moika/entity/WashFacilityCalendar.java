package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.hql.internal.ast.ParseErrorHandler;

import javax.persistence.*;
import java.text.ParseException;
import java.util.*;

/**
 * WashFacilityTimeTable - Сущность описывающая расписание работы(нерабочие часы)  автомойки
 * Сущность, описывающая рабочий календарь (выходнные или нестандартные дни) для автомойки, отличные от регулярнрых рабочих недель,
 * реализуемых в WashFacilityWeekDay
 */
@Entity(name = "facility_calendar")
@IdClass(FacilityCalendarPk.class)
public class WashFacilityCalendar extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    protected int idFclt;
    @Id
    @Column(name = "date_x")
    protected Date dateX;

    @Column(name = "id_date_type", insertable = false, updatable = false)
    private int idDateType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_date_type", foreignKey = @ForeignKey(name = "fk_calendar_types"))
    private CalendarDateType dateType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumns(value = {
            @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt"),
            @JoinColumn(name = "date_x", referencedColumnName = "date_x")}, foreignKey = @ForeignKey(name = "fk_facility_time_table_facility_calendar"))
    private Set<WashFacilityTimeTable> fcltTimeTable = new HashSet<>();


    public WashFacilityCalendar() {
    }

    public WashFacilityCalendar(int idFacility, Date dateX, CalendarDateType dateType) {
        this.idFclt = idFacility;
        this.dateX = dateX;
        this.dateType = dateType;
    }


    public int getIdFclt() {
        return idFclt;
    }

    public void setIdFclt(int idFclt) {
        this.idFclt = idFclt;
    }

    public Date getCalendarDate() {
        return dateX;
    }


    public int getIdDateType() {
        return idDateType;
    }

    public void setIdDateType(int idDateType) {
        this.idDateType = idDateType;
    }

    public CalendarDateType getDateType() {
        return dateType;
    }

    public void setDateType(CalendarDateType dateType) {
        this.dateType = dateType;
    }

    public List<WashFacilityTimeTable> getFcltTimeTable() {
        return new ArrayList<>(fcltTimeTable);
    }

    public void setFcltTimeTable(Set<WashFacilityTimeTable> fcltTimeTable) {
        this.fcltTimeTable = fcltTimeTable;
    }

    @JsonIgnore
    public List<WorkHours> getWorkHours(){
        List<WorkHours> wh = new ArrayList<>();
        fcltTimeTable.forEach(tt -> wh.add(tt.getWorkHours()));
        return wh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityCalendar)) return false;
        WashFacilityCalendar that = (WashFacilityCalendar) o;
        return idFclt == that.idFclt &&
                Objects.equals(dateX, that.dateX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, dateX);
    }
}
