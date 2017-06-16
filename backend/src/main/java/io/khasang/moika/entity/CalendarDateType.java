package io.khasang.moika.entity;

import javax.persistence.Entity;;

/**
 * Сужность описывающая типы дат в календарях
 */
@Entity(name = "calendar_date_types")
public class CalendarDateType extends ABaseMoikaTypeReference {

    public CalendarDateType() {
    }

    public CalendarDateType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CalendarDateType(String code) {
        this.code = code;
    }

}
