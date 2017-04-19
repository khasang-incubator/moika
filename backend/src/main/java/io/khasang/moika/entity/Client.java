package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.*;

@Entity(name = "clients")
public class Client extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_client", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JoinColumn(name="id_person")
    protected Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        StringBuffer telStr = new StringBuffer();
        person.getPhones().forEach((Phone p)-> telStr.append(p.getNumber() + ",\n" ));
        return "Client{" +
                "id=" + id +
                ", Full Name='" + person.getFullName() + '\n' +
                ", tel='" + telStr.toString() +
                ", dateReg=" + dateReg +  '\n' +
                ", status=" + status +  '\n' +
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
