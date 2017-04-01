package io.khasang.moika.entity;

import org.hibernate.annotations.JoinColumnsOrFormulas;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Cats implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "number")
    private long id;
    private Date additionalInfo;
    private String description;

    public Cats() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Date additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}