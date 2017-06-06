package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "other_services")
@DiscriminatorValue(value = "OTHER")
public class OtherService extends MoikaService{
    public OtherService() {
        super("OTHER");
    }
}
