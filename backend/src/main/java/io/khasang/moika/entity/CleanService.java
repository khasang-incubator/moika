package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * CleanService - Сущность описывающая услуги чиски салона авто.
 * Наследуется от общей сущности услуг автомойки
 * Связана с
 * - типом загрязнений, от которого зависит цена услуги
 */
@Entity(name = "clean_Services")
@DiscriminatorValue(value = "CLEAN")
public class CleanService extends MoikaService {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable = false, updatable = false)
    private DirtType dirtTypeEntity;

    public CleanService() {
        super("CLEAN");
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
