package io.khasang.moika.entity;

import javax.persistence.*;
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
    private Person person;
    @OneToOne(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PhoneDetails details;

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

    public PhoneDetails getDetails() {
        return details;
    }
    public void addDetails(PhoneDetails details) {
        details.setPhone( this );
        this.details = details;
    }

    public void removeDetails() {
        if ( details != null ) {
            details.setPhone( null );
            this.details = null;
        }
    }
}
