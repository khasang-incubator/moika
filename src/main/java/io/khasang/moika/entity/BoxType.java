package io.khasang.moika.entity;

import javax.persistence.*;

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
