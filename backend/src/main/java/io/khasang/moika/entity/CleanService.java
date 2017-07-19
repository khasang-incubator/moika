package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * CleanService - Сущность описывающая услуги чиски салона авто.
 * Наследуется от общей сущности услуг автомойки
 * Связана с
 * - типом загрязнений, от которого зависит цена услуги
 */
@Entity(name = "clean_Services")
@Inheritance(strategy = InheritanceType.JOINED)
public class CleanService extends MoikaService {

    @Column(name = "id_dirt_type", insertable = false, updatable = false)
    private int idDirtType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_clean_dirt_type"))
    @JsonBackReference
    protected DirtType dirtTypeEntity;


    public CleanService() {

    }

    public int getIdDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }

    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }

}
