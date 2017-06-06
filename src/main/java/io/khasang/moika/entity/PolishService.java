package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "polish_services")
@DiscriminatorValue(value = "POLISH")
public class PolishService extends MoikaService {

    public PolishService() {
        super("POLISH");
    }
}
