package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Сущность описывающая авто, с которым будут что-то делать или уже делали
 */
@Entity(name = "cars")
public class Car extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_car", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idCar;
    @Column(name = "id_car_type", insertable = false, updatable = false)
    private int idCarType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car_type", foreignKey = @ForeignKey(name = "fk_car_type"))
    private CarType carTypeEntity;
    @Column (name = "carnum")
    private String carNumber;
    @Column(name = "carmodel")
    private String carModel;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Short status = 1;
    @Column(name = "note")
    private String note;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReg;
    @Column(name = "date_Last_Wash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_car", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_client",
                    nullable = false, updatable = false)})
    @JsonIgnore
    private List<Client> clients = new ArrayList<>();

    public Car() {
        dateReg = new Date();
    }

    public long getId() {
        return idCar;
    }

    public void setId(long id) {
        this.idCar = id;
    }
    public int getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(int idCarType) {
        this.idCarType = idCarType;
    }

    public CarType getCarTypeEntity() {
        return this.carTypeEntity;
    }

    public void setCarTypeEntity(CarType carTypeEntity) {
        this.carTypeEntity = carTypeEntity;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNum) {
        this.carNumber = carNum;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public Date getDateLastWash() {
        return dateLastWash;
    }

    public void setDateLastWash(Date dateLastWash) {
        this.dateLastWash = dateLastWash;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void changeClient(Client oldClient, Client newClient) {
        clients.remove(oldClient);
        clients.add(newClient);
    }


}
