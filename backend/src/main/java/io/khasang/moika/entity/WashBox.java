package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * WashBox - Сущность, представляющая моечный бокс, из которых состоят автомойки
 * Связана с:
 *  - автомойкой, которй принадлежит
 *  - имеет тип, от которого зависит, какие машины и как могут обслуживаться
 *  - статус, который определяет срабочее состояние бокса
 */
@Entity(name = "wash_boxes")
public class WashBox  extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box")   // columnDefinition = "serial"
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "id_fclt", insertable=false, updatable=false)
    private int idFacility;
    @ManyToOne
    @JoinColumn(name = "id_fclt", insertable=false, updatable=false )
    @JsonBackReference(value = "fclt-boxes")
    private WashFacility washFacility;

    @Column(name = "name")
    private String boxName;

    @Column(name = "descr")
    private String description;


    @Column(name = "id_type", insertable=false, updatable=false)
    private int idType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn( name = "id_type")//, foreignKey = @ForeignKey(name = "fk_box_type"), insertable=false, updatable=false )
    private BoxType boxTypeEntity;


    @Column(name = "id_status", insertable=false, updatable=false)
    private Short idStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_status")//, insertable=false, updatable=false )
    private BoxStatus boxStatusEntity;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_box", foreignKey = @ForeignKey(name = "fk_box_time_table_wash_boxes"))
    @Fetch(FetchMode.SELECT)
 //   @JsonManagedReference
 //   @JsonIgnore
    private Set<WashBoxTimeTable> boxTimeTable = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_box", foreignKey = @ForeignKey(name = "box_week_days_wash_box_id_box_fk"))
    @Fetch(FetchMode.SELECT)
  //  @JsonManagedReference
  //  @JsonIgnore
    private Set<WashBoxWeekTimeTable> boxWeekTimeTable = new HashSet<>();

    public WashBox() {
    }

    public WashBox(int idFacility, String name, int idBoxType ) {
        this.boxName = name;
        this.boxTypeEntity = new BoxType("CAR");
    }

    public int getId() {
        return id;
    }

    public int getIdFacility() {
        return idFacility;//washFacility.getId();
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int id_type) {
        this.idType = id_type;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Short boxStatus) {
        this.idStatus = boxStatus;
    }


    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public BoxType getBoxTypeEntity() {
        return boxTypeEntity;
    }

    public BoxStatus getBoxStatusEntity() {
        return boxStatusEntity;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setWashFacility(WashFacility washFacility) {
       this.washFacility = washFacility;
    }

    public void setBoxTypeEntity(BoxType boxTypeEntity) {
        this.boxTypeEntity = boxTypeEntity;
        this.setIdType(boxTypeEntity.getId());
    }

    public void setBoxStatusEntity(BoxStatus boxStatusEntity) {
        this.boxStatusEntity = boxStatusEntity;
        this.setIdStatus((short) boxStatusEntity.getId());
    }

    public Set<WashBoxTimeTable> getBoxTimeTable() {
        return boxTimeTable;
    }

    public void setBoxTimeTable(Set<WashBoxTimeTable> boxTimeTable) {
        this.boxTimeTable = boxTimeTable;
    }

    public Set<WashBoxWeekTimeTable> getBoxWeekTimeTable() {
        return boxWeekTimeTable;
    }

    public void setBoxWeekTimeTable(Set<WashBoxWeekTimeTable> boxWeekTimeTable) {
        this.boxWeekTimeTable = boxWeekTimeTable;
    }

    @Override
    public String toString() {
        return "WashBox{" +
                "id=" + id +
                ", idFacility=" + getIdFacility()+
                ", boxName='" + boxName + '\'' +
                ", boxTypeEntity=" + boxTypeEntity.toString() +
                ", boxStatusEntity=" + boxStatusEntity.toString() + '\'' +
                ", description='" + (description == null ? "" : description)  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashBox)) return false;

        WashBox washBox = (WashBox) o;

        if (getId() != washBox.getId()) return false;
        if (washFacility.getId() != washBox.getIdFacility()) return false;
        if (!getBoxName().equals(washBox.getBoxName())) return false;
        return getDescription() != null ? getDescription().equals(washBox.getDescription()) : washBox.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + washFacility.getId();
        result = 31 * result + getBoxName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
