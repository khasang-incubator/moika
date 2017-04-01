package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.*;

@Entity(name = "clients")
public class Client extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_client", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "tel")
    private String phone;
    @Column(name = "status", nullable = false)
    private Short status;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReg;
    @Column(name = "date_Last_Wash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_client", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_car",
                    nullable = false, updatable = false) })
    private List<Car> cars = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName, String middleName, String lastname, String phone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Client(String firstName, String middleName, String lastname, String phone, List<Car> cars) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.phone = phone;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String tel) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car){
        getCars().add(car);
    }

    public void removeCar(Car car){
        getCars().remove(car);
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", tel='" + phone + '\'' +
                ", dateReg=" + dateReg +
                ", status=" + status +
                ", dateLastWash=" + dateLastWash +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return (getId() == client.getId() && dateReg.equals(((Client) o).getDateReg()));
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
