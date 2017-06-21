package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "r_facility_phones",
            joinColumns = @JoinColumn(name = "id_fclt"),
             inverseJoinColumns = @JoinColumn(name = "id_phone"),
              uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
    @JsonManagedReference
    protected Set<Phone> phones = new HashSet<>(); //важно, что бы была EAGER инициализация и не дрался с другими ome to many у которых так же EAGER инициальзация
    //иначе будет ошибка:  org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference
    private List<WashBox> washBoxes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonBackReference //важно именно BackReference при lazy инициализации иначе будет драться с другими List<>
    private List<Orders> orders = new ArrayList<>();

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
   // @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private Set<WashFacilityCalendar> oddOffDays = new HashSet<>();

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt",  foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
    // @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private Set<WashFacilityWeekDay> weekOffDays = new HashSet<>();


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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = new HashSet<>(phones);
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public void addPhone(String phone) {
        this.phones.add(new Phone(phone));
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

    public void setWashBoxes(List<WashBox> washBoXes) {
        this.washBoxes = washBoXes;
    }

    public List<Orders> getOrders() {
        return orders;
    }


    public List<WashFacilityCalendar> getFcltOddOffDays() {
        return new ArrayList<>(oddOffDays);
    }

    @JsonIgnore
    public void setFcltOddOffDays(List<WashFacilityCalendar> oddOffDays) {
        this.oddOffDays = new HashSet<>(oddOffDays);
    }

    public List<WashFacilityWeekDay> getFcltWeekOffDays() {
        return new ArrayList<>(weekOffDays);
    }

    @JsonIgnore
    public void setFcltWeekOffDays(Set<WashFacilityWeekDay> weekOffDays) {
        this.weekOffDays = weekOffDays;
    }


    public void setFcltWeekOffDays(List<WashFacilityWeekDay> weekOffDays) {
        this.weekOffDays = new HashSet<>(weekOffDays);
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
        return Objects.hash(getId(), getIdNet(), getName());
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
