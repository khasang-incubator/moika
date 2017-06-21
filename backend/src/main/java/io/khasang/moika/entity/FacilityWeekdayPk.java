package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

/**
 * Сужность составного первичного ключа для рабочего календаря
 */
@Embeddable
public class FacilityWeekdayPk extends ABaseMoikaEntity{

    @Column(name = "id_fclt")
    protected int idFclt;
    @Column(name = "day_of_week")
    protected DayOfWeek weekDay;


    public FacilityWeekdayPk() {
    }

    public FacilityWeekdayPk(int idFclt, int weekDay) {
        this.idFclt = idFclt;
        this.weekDay = DayOfWeek.of(weekDay);
    }

    public FacilityWeekdayPk(int idFclt, DayOfWeek weekDay) {
        this.idFclt = idFclt;
        this.weekDay = weekDay;
    }

    public int getIdFaciilty() {
        return idFclt;
    }

    public void setIdFacility(int idFclt) {
        this.idFclt = idFclt;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = DayOfWeek.of(weekDay);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityWeekdayPk that = (FacilityWeekdayPk) o;
        return idFclt == that.idFclt && weekDay.equals(that.weekDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFclt, weekDay);
    }
}
