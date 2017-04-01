package io.khasang.moika.entity;

import javax.persistence.*;

@Entity (name = "box_status")
public class BoxStatus extends ABaseMoikaStatusReference{

    public BoxStatus() {
    }

    public BoxStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public BoxStatus(String code) {
        this.code = code;
    }

}
