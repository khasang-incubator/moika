package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "wash_services")
@DiscriminatorValue(value = "WASH")
public class WashService extends MoikaService {

    @Column(name = "id_car_type", insertable = false, updatable = false)
    private int idCarType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_car_type",  referencedColumnName = "id_type") //foreignKey = @ForeignKey(name = "fk_car_type"),
    @JsonBackReference
    private CarType carTypeEntity;

    public WashService() {
        super("WASH");
    }

    public int getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(int idCarType) {
        this.idCarType = idCarType;
    }

    public CarType getCarTypeEntity() {
        return carTypeEntity;
    }

    public void setCarTypeEntity(CarType carTypeEntity) {
        this.carTypeEntity = carTypeEntity;
        this.setIdCarType(carTypeEntity.getId());
    }

}
