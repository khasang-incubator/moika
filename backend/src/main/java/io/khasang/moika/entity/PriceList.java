package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, описывающая price-list
 */

@Entity(name = "service_types")
public class PriceList  {

 /*   //@OneToMany(mappedBy="serviceTypeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;
 */

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_code", unique = true, nullable = false)
    @NaturalId
    protected EServiceType serviceType;
    @Column(name = "type_name")
    protected String name;
    @Column(name = "descr")
    protected String description;

    @Where( clause = "id_type = 1")
    @OneToMany(mappedBy = "serviceType")
    private List<WashService> washServices = new ArrayList<>( );

    @Where( clause = "id_type = 2")
    @OneToMany(mappedBy = "serviceType")
    private List<CleanService> cleanServices = new ArrayList<>( );

    @Where( clause = "id_type = 3")
    @OneToMany(mappedBy = "serviceType")
    private List<ChemCleanService> chemCleanServices = new ArrayList<>( );

    public PriceList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(EServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WashService> getWashServices() {
        return washServices;
    }

    public void setWashServices(List<WashService> washServices) {
        this.washServices = washServices;
    }

    public List<CleanService> getCleanServices() {
        return cleanServices;
    }

    public void setCleanServices(List<CleanService> cleanServices) {
        this.cleanServices = cleanServices;
    }

    public List<ChemCleanService> getChemCleanServices() {
        return chemCleanServices;
    }

    public void setChemCleanServices(List<ChemCleanService> chemCleanServices) {
        this.chemCleanServices = chemCleanServices;
    }


}
