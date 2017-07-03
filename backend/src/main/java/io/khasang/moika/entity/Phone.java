package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.util.Objects;

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

 //   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
 //   @JoinTable(name = "r_facility_phones",
 //           joinColumns = @JoinColumn(name = "id_phone"),
 //           inverseJoinColumns = @JoinColumn(name = "id_fclt"),
 //           uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
 //   @JsonBackReference(value = "fclt-phones")
 //   private WashFacility fclt;

  //  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  //  @JoinTable(name = "r_person_phones",
  //           joinColumns = @JoinColumn(name = "id_phone"),
  //           inverseJoinColumns = @JoinColumn(name = "id_person"),
  //           uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
   // @JsonBackReference(value = "person-phones")
   // private Person person;

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
        Phone phone = (Phone) o;
        return Objects.equals(getPhoneNumber(), phone.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
