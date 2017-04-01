package io.khasang.moika.entity;

import javax.persistence.Entity;

@Entity(name = "salon_materials")
public class SalonMaterial extends ABaseMoikaTypeReference {

    public SalonMaterial() {
    }

    public SalonMaterial(String typeCode) {
        this.code = typeCode;
    }

    public SalonMaterial(String typeCode, String typeName) {
        this.code = typeCode;
        this.name = typeName;
    }


}
