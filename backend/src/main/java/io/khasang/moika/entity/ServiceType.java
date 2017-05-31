package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "service_types")
public class ServiceType extends ABaseMoikaTypeReference {

 /*   //@OneToMany(mappedBy="serviceTypeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;
 */
    public ServiceType() {
    }

    public ServiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

  //  public List<MoikaService> getMoikaServices() {
  //       return moikaServices;
  //  }
}
