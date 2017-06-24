package io.khasang.moika.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Сущность, описывающая рабочие часы (начало,окончание)
 */
@Embeddable
public class WorkHours {
    private static final Logger logger = LoggerFactory.getLogger(WorkHours.class);
    @Id
    @Column(name = "time_on_starts")
    protected LocalTime timeOnStarts = LocalTime.of(0, 0);

    @Column(name = "time_on_ends")
    protected LocalTime timeOnEnds = LocalTime.of(0, 0);

    public WorkHours() {
    }

    public WorkHours(LocalTime start, LocalTime end) {
        this.timeOnStarts = start;
        this.timeOnEnds = end;
    }


    public WorkHours(String start, String end) {
        try {
            this.timeOnStarts = LocalTime.parse(start);
            this.timeOnEnds = LocalTime.parse(end);
        } catch (DateTimeParseException e) {
            logger.error("Incorrect local time initialization!!! start:" + start + " : " + "end:" + end);
        }
    }

    public LocalTime getTimeOnStarts() {
        return timeOnStarts;
    }

    public void setTimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    public LocalTime getTimeOnEnds() {
        return timeOnEnds;
    }

    public void setTimeOnEnds(LocalTime timeOnEnds) {
        this.timeOnEnds = timeOnEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkHours)) return false;
        WorkHours workHours = (WorkHours) o;
        return Objects.equals(timeOnStarts, workHours.timeOnStarts) &&
                Objects.equals(timeOnEnds, workHours.timeOnEnds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeOnStarts, timeOnEnds);
    }
}
