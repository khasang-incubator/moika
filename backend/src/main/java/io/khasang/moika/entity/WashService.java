package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * WashService - Сущность описывающая услуги помывки авто.
 * Наследуется от общей сущности услуг автомойки
 * Связана с
 * - типом авто, от которого зависит цена услуги
 */
@Entity(name = "wash_services")
@DiscriminatorValue(value = "WASH")
public class WashService extends MoikaService {



    public WashService() {
        super("WASH");
    }



}
