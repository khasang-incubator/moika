package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pauls on 13.06.2017.
 */
@Entity(name = "box_time_table")
public class WashBoxTimeTable extends ABaseMoikaEntity {

    @EmbeddedId
    private TimeTablePk boxTymeTableId;

    @Column(name = "time_off_end")
    @Temporal(TemporalType.TIME)
    protected Date timeOffEnds;



}
