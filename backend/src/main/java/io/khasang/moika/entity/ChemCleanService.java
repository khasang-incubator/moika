package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * CleanService - Сущность описывающая услуги чиски салона авто.
 * Наследуется от общей сущности услуг автомойки
 * Связана с
 * - типом загрязнений, от которого зависит цена услуги
 */
@Entity(name= "chem_clean_services")
public class ChemCleanService extends MoikaService  {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_chem_dirt_type"), insertable=false, updatable=false )
    private DirtType dirtTypeEntity;

    @Column(name = "id_material")
    private int idMaterial;
    @ManyToOne
    @JoinColumn(name = "id_material", foreignKey = @ForeignKey(name = "fk_chem_salon_material"), insertable=false, updatable=false )
    private SalonMaterial salonMaterial;

    public ChemCleanService() {

    }

    public int getIdDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(Short idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Short idMaterial) {
        this.idMaterial = idMaterial;
    }

    public SalonMaterial getSalonMaterial() {
        return salonMaterial;
    }

    public void setSalonMaterial(SalonMaterial salonMaterial) {
        this.salonMaterial = salonMaterial;
    }

    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }

}
