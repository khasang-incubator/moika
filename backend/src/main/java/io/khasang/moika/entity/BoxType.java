package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * Сущность, описывающая типы моечных боксов
 * От этого зависит какие типы авто в них могнут обслуживаться, а соответственно это влияет на заказы, работы и очереди
 */
@Entity(name = "box_types")
public class BoxType extends ABaseMoikaTypeReference {

    public BoxType() {
    }

    public BoxType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public BoxType(String code) {
        this.code = code;
    }

}
