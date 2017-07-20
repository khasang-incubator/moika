package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "service_groups")
public class ServiceGroup extends ABaseMoikaTypeReference {

  /*  @JoinTable(name = "r_service_type_group",
            joinColumns = @JoinColumn(name = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "id_group"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"pk_service_type_group"}))
    //@JsonManagedReference(value = "person-phones")
    @Fetch(FetchMode.SELECT) */

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "r_service_type_group",
            joinColumns = @JoinColumn(name = "id_group"),
            inverseJoinColumns = @JoinColumn(name = "id_type"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_type", "id_group"}))
    @JsonBackReference
    private ServiceType serviceType;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_group",  insertable = false, updatable = false)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private List<MoikaService> moikaServices;

    public ServiceGroup() {
    }

    public ServiceGroup(String code, String name) {
        super(code, name);
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

    public void setMoikaServices(List<MoikaService> moikaServices) {
        this.moikaServices = moikaServices;
    }


}
