package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * PolishService - Сущность описывающая услуги полировки корпуса авто.
 * Наследуется от общей сущности услуг автомойки
 */
@Entity(name = "polish_services")
public class PolishService extends MoikaService {

    @Column(name = "id_polish_type", insertable = false, updatable = false)
    private int idPolishType;

    public PolishService() {
    }

}
