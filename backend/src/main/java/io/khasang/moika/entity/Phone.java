package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import static io.khasang.moika.util.DataValidationPatterns.PHONE_NUMBER_PATTERN;

/**
 * Сущность, описывающая телефоны клиентов
 */

@Entity(name="phones")
public class Phone extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_phone")
    private Long id;
    @Column(name="phone_number")
    @NotBlank
    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = "{phone.not_10digits.message}")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", foreignKey = @ForeignKey(name = "fk_person_phone"))
    @JsonBackReference
    private Person person;


    public Phone() {
    }
    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + phoneNumber + '\'' +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone uphone = (Phone) o;

        if (getId() != null ? !getId().equals(uphone.getId()) : uphone.getId() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(uphone.getPhoneNumber()) : uphone.getPhoneNumber() != null) return false;
        return getPerson() != null ? getPerson().equals(uphone.getPerson()) : uphone.getPerson() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        return result;
    }
}
