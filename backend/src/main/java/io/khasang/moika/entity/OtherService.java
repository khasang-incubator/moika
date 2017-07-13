package io.khasang.moika.entity;

import javax.persistence.*;
/**
 * OtherService - Сущность описывающая всяуие прочие нестандартные услуги (типа чернение резины или мойка велосипеда)
 * Наследуется от общей сущности услуг автомойки
 */
@Entity(name = "other_services")
public class OtherService extends MoikaService{
    public OtherService(){
    }
}
