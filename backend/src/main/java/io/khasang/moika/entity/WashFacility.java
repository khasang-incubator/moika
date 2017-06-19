package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * WashFacility - Сущность представляющая автомойки.
 * Связана с
 * - адресом
 * - менеджером
 * - моечными боксами, из клоторых состоит
 * - производственным календарем, который определяет время работы (not yet ready)*
 * - очередью машин, записанных на помывку (not yet ready)*
 */
@Entity(name = "wash_facilities")
public class WashFacility  extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_fclt") //, columnDefinition = "serial"
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "id_net")
    private int idNet;
    @Column(name = "id_manager")
    private int  idManager;
    @Column(name = "name")
    private String  name ;
    @Column(name = "descr")
    private String  description;

    @Column(name = "id_addr")
    private int  idAddr;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_addr", insertable = false, updatable = false)
    private WashAddr facilityAddr;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "r_facility_phones",
            joinColumns = @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt"),
             inverseJoinColumns = @JoinColumn(name = "id_phone", referencedColumnName = "id_phone"),
              uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
  //  @JoinColumn( name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference
    protected List<Phone> phones = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference
    private List<WashBox> washBoxes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonBackReference
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference
    private List<WashFacilityCalendar> fcltCalendar = new ArrayList<>();

    public WashFacility() {
    }

    public int getId() {
        return id;
    }

    public int getIdNet() {
        return idNet;
    }

    public void setIdNet(int idNet) {
        this.idNet = idNet;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdAddr() {
        return idAddr;
    }

    public void setIdAddr(int idAddr) {
        this.idAddr = idAddr;
    }

    public WashAddr getFacilityAddr() {
        return facilityAddr;
    }

    public void setFacilityAddr(WashAddr facilityAddr) {
        this.facilityAddr = facilityAddr;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public void addPhone(String phone) {
        Phone aPhone = new Phone(phone);
        this.phones.add(aPhone);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WashBox> getWashBoxes() {
        return washBoxes;
    }

    public void setWashBoxes(List<WashBox> washBowes) {
        this.washBoxes = washBowes;
    }

    public List<Orders> getOrders() {
        return orders;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacility)) return false;

        WashFacility that = (WashFacility) o;

        if (getId() != that.getId()) return false;
        if (getIdNet() != that.getIdNet()) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdNet();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WashBox box : washBoxes) {
            sb.append(box.toString());
            sb.append("\n");
        }
        return "WashFacility{" +
                "id=" + id +
                ", idNet=" + idNet +
                ", idManager=" + idManager +
                ", name='" + name + '\'' +
                ", facilityAddr=" + (( facilityAddr != null) ? facilityAddr.toString() : "") + '\''+
                ", description='" + description + '\'' +
                ", washBoxes=" + sb.toString() +
                '}';
    }
}
