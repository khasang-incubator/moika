package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.*;


@Entity(name = "cars")
public class Car {

    @Id
    @Column(name = "id_car", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_car_type")
    private Short idCarType;
    @ManyToOne
    @JoinColumn(name = "id_car_type", foreignKey = @ForeignKey(name = "fk_car_type"), insertable=false, updatable=false )
    private CarType CarTypeEntity;

    @Column(name = "carnum")
    private String carNumber;
    @Column(name = "carmodel")
    private String carModel;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Short status;
    @Column(name = "note")
    private String note;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReg;
    @Column(name = "date_Last_Wash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_car", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_client",
                    nullable = false, updatable = false) })
    private List<Client> clients = new ArrayList<>();

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(Short idCarType) {
        this.idCarType = idCarType;
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

    public Calendar getDateReg() {
        return dateReg;
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

    public void changeClient(Client oldClient, Client newClient){
        clients.remove(oldClient);
        clients.add(newClient);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", CarTypeEntity=" + CarTypeEntity +
                ", carNumber='" + carNumber + '\'' +
                ", carModel='" + carModel + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", dateReg=" + dateReg +
                ", dateLastWash=" + dateLastWash +
                '}';
    }
}
