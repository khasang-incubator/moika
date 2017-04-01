package io.khasang.moika.entity;

import javax.persistence.Entity;

@Entity(name = "dirt_types")
public class DirtType extends ABaseMoikaTypeReference {

    public DirtType() {
    }

    public DirtType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
