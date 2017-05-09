package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import static io.khasang.moika.util.DataValidationPatterns.PHONE_NUMBER_PATTERN;

@Entity(name="phones")
public class Phone extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long id;
    @Column(name="phone_number")
    @NotBlank
    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = "{phone.not_10digits.message}")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", foreignKey = @ForeignKey(name = "fk_person_phone"))
    @JsonBackReference
    private Person person;


    public Phone() {
    }
    public Phone(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone uphone = (Phone) o;

        if (getId() != null ? !getId().equals(uphone.getId()) : uphone.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(uphone.getNumber()) : uphone.getNumber() != null) return false;
        return getPerson() != null ? getPerson().equals(uphone.getPerson()) : uphone.getPerson() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        return result;
    }
}
