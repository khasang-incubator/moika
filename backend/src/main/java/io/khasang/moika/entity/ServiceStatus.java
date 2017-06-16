package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность, описывающая статуспредоставляемых автомойкой услушг
 */
@Entity (name = "service_status")
public class ServiceStatus extends ABaseMoikaStatusReference{

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;

    public ServiceStatus() {
    }

    public ServiceStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<MoikaService> getMoikaServices() {
        return moikaServices;
    }
}
