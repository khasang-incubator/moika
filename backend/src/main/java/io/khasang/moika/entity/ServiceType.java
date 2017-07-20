package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сущность, описывающая типы цслуг, предоставляемы мойками (мока, чистка, полировак и т.р.)
 */

@Entity(name = "service_types")
public class ServiceType extends ABaseMoikaTypeReference {

    /*@OneToMany(mappedBy="serviceTypeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @JoinTable(name = "r_service_type_group",
           joinColumns =  @JoinColumn(name = "id_group"),
           inverseJoinColumns = @JoinColumn(name = "id_type"))
    */
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "r_service_type_group",
            joinColumns =  @JoinColumn(name =  "id_type"),
            inverseJoinColumns = @JoinColumn(name ="id_group"))
    @JsonIgnore
    private List<ServiceGroup> serviceGroups = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    @JsonIgnore
    private Set<MoikaService> moikaServices = new HashSet<>();

    public ServiceType() {
    }

    public ServiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<ServiceGroup> getServiceGroups() {
        return serviceGroups;
    }

    public void setServiceGroups(List<ServiceGroup> serviceGroups) {
        this.serviceGroups = serviceGroups;
    }

    public Set<MoikaService> getMoikaServices() {
        return moikaServices;
    }

}
