package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, описывающая price-list
 */

@Entity

public class PriceList  {

 /*   //@OneToMany(mappedBy="serviceTypeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;
 */

    @Id
    protected int id;

    @Enumerated(EnumType.ORDINAL)
    @NaturalId
    protected EServiceType serviceType;

    protected String name;

    protected String description;

 //   @Where( clause = "id_type = 1")
 //   @OneToMany(mappedBy = "serviceType")
    @ElementCollection(targetClass=WashService.class)
    protected List<WashService> washServices = new ArrayList<>( );

  //  @Where( clause = "id_type = 2")
  //  @OneToMany(mappedBy = "serviceType")
   @ElementCollection(targetClass=CleanService.class)
    protected List<CleanService> cleanServices = new ArrayList<>( );

  //  @Where( clause = "id_type = 3")
  //  @OneToMany(mappedBy = "serviceType")
    @ElementCollection(targetClass=ChemCleanService.class)
    protected List<ChemCleanService> chemCleanServices = new ArrayList<>( );

  //  @Where( clause = "id_type = 4")
   // @OneToMany(mappedBy = "serviceType")
    @ElementCollection(targetClass=PolishService.class)
    protected List<PolishService> polishServices = new ArrayList<>( );

 //   @Where( clause = "id_type = 5")
 //   @OneToMany(mappedBy = "serviceType")
    @ElementCollection(targetClass=OtherService.class)
    protected List<OtherService> otherServices = new ArrayList<>( );

 //   @Where( clause = "id_type = 6")
//    @OneToMany(mappedBy = "serviceType")
    @ElementCollection(targetClass=ComplexService.class)
    protected List<ComplexService> complexServices = new ArrayList<>( );

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

    public List<PolishService> getPolishServices() {
        return polishServices;
    }

    public void setPolishServices(List<PolishService> polishServices) {
        this.polishServices = polishServices;
    }

    public List<OtherService> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<OtherService> otherServices) {
        this.otherServices = otherServices;
    }

    public List<ComplexService> getComplexServices() {
        return complexServices;
    }

    public void setComplexServices(List<ComplexService> complexServices) {
        this.complexServices = complexServices;
    }
}
