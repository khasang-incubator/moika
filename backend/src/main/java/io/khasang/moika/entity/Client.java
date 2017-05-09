package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity(name = "clients")
public class Client extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_client", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_person", insertable=false, updatable=false)
    private int idPerson;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name="id_person")
    @JsonManagedReference
    protected Person person;

    @Column(name = "id_status", nullable = false , insertable=false, updatable=false)
    private Short idStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_status")//, insertable=false, updatable=false )
    private ClientStatus status;

    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReg;

    @Column(name = "date_Last_Wash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_client", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_car",
                    nullable = false, updatable = false) })
    @JsonIgnore
    private List<Car> cars = new ArrayList<>();

    public Client() {
    }

    public Client(Person person) {
        this.person = person;
    }

    public Client(String firstName, String middleName, String lastname, String phone, List<Car> cars) {
        this.person.setFirstName(firstName);
        this.person.setMiddleName(middleName);
        this.person.setLastName(lastname);
        this.person.addPhone(phone);
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public Short getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Short idStatus) {
        this.idStatus = idStatus;
    }

    public void setDateReg(Calendar dateReg) {
        this.dateReg = dateReg;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
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
        StringBuffer telStr = new StringBuffer();
        person.getPhones().forEach((Phone p)-> telStr.append(p.getNumber() + ",\n" ));
        return "Client{" +
                "id=" + id +
                ", Full Name='" + person.getFullName() + '\n' +
                ", tel='" + telStr.toString() +
                ", dateReg=" + dateReg +  '\n' +
                ", status=" + status.code +  '\n' +
                ", dateLastWash=" + dateLastWash + '\n' +
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
