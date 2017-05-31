package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "service_subtypes")
public class ServiceSubType extends ABaseMoikaTypeReference {

    @Column(name = "id_base_type",  insertable = false, updatable = false)
    protected int idBaseType;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_base_type", referencedColumnName = "id_type", foreignKey = @ForeignKey(name = "fk_base_type") )
    @JsonIgnore
    ServiceType serviceType;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subtype",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;

    public ServiceSubType() {
    }

    public ServiceSubType(String code, String name) {
        super(code, name);
    }

    public int getIdBaseType() {
        return idBaseType;
    }


    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<MoikaService> getMoikaServices() {
        return moikaServices;
    }
}
