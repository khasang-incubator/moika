package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.text.ParseException;
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
public class WashFacility extends ABaseMoikaEntity {
    private static final Logger logger = LoggerFactory.getLogger(WashFacility.class);
    @Id
    @Column(name = "id_fclt") //, columnDefinition = "serial"
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "id_net")
    private int idNet;
    @Column(name = "id_manager")
    private int idManager;
    @Column(name = "name")
    private String name;
    @Column(name = "descr")
    private String description;

    @Column(name = "id_addr")
    private int idAddr;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_addr", insertable = false, updatable = false)
    private WashAddr facilityAddr;

 /*   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "r_facility_phones",
            joinColumns = @JoinColumn(name = "id_fclt"),
            inverseJoinColumns = @JoinColumn(name = "id_phone"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
 //   @JsonManagedReference(value = "fclt-phones")
   // @JsonBackReference
    @JsonIgnore
    protected Set<Phone> phones = new HashSet<>(); //важно, что бы была EAGER инициализация и не дрался с другими ome to many у которых так же EAGER инициальзация
    //иначе будет ошибка:  org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
*/
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference(value = "fclt-boxes")
    private List<WashBox> washBoxes = new ArrayList<>();

    /*
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference(value = "orders-fclt")
    //@JsonIgnore
    private Set<Orders> orders = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt", foreignKey = @ForeignKey(name = "fk_fclt_calendar_facility"))
    // @Fetch(value = FetchMode.SUBSELECT)
    //  @JsonManagedReference(value = "fclt-odd-days")
    // @JsonIgnore
    private Set<WashFacilityCalendar> oddOffDays = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt", foreignKey = @ForeignKey(name = "fk_fclt_time_table_facility"))
    //   @JsonManagedReference(value = "fclt-week-days")
    //  @JsonIgnore
    private Set<WashFacilityWeekDay> weekOffDays = new HashSet<>();
*/

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
/*
    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @JsonIgnore
    public void setPhones(List<Phone> phones) {
        this.phones = new HashSet<>(phones);
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    @JsonIgnore
    public void addPhone(String phone) {
        this.phones.add(new Phone(phone));
    }
*/
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

  //  @JsonIgnore
 //   public void setWashBoxes(List<WashBox> washBoXes) {
 //       this.washBoxes = new HashSet<>(washBoXes);
 //   }
/*
    public Set<Orders> getOrders() {
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


    public void setFcltWeekOffDays(Set<WashFacilityWeekDay> weekOffDays) {
        this.weekOffDays = weekOffDays;
    }

    @JsonIgnore
    public void setFcltWeekOffDays(List<WashFacilityWeekDay> weekOffDays) {
        this.weekOffDays = new HashSet<>(weekOffDays);
    }

    @JsonIgnore
    public List<WorkHours> getDefaultWorkHours() {
        Date defDate;
        List<WorkHours> resHours = null;
        try {
            defDate = DateUtils.parseDate("1990-01-01", "Y-M-D");
        } catch (ParseException e) {
            defDate = new Date();
        }
        for (WashFacilityCalendar fcltResc : this.getFcltOddOffDays()) {
            if (fcltResc.getCalendarDate().compareTo(defDate) == 0 ) {
                if (fcltResc.getIdDateType() == 0) {
                    resHours = fcltResc.getWorkHours();
                    break;
                }
            }
        }
        return resHours;
    }
*/
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
        washBoxes.forEach(box -> {
            sb.append(box.toString());
            sb.append("\n");
        });
        return "WashFacility{" +
                "id=" + id +
                ", idNet=" + idNet +
                ", idManager=" + idManager +
                ", name='" + name + '\'' +
                ", facilityAddr=" + ((facilityAddr != null) ? facilityAddr.toString() : "") + '\'' +
                ", description='" + description + '\'' +
                ", washBoxes=" + sb.toString() +
                '}';
    }
}
