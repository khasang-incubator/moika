package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

/**
 * Базовый абстрактный класс для всех таблиц статусов xxx_status
 * обязательные поля: id_status, code, name
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABaseMoikaStatusReference extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_status", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    @Column(name = "code", unique = true, nullable = false)
    @NaturalId
    protected String code;
    @Column(name = "name")
    protected String name;


    public int getId() {
        return id;
    }

    public String getStatusCode() {
        return code;
    }

    public void setStatusCode(String code) {
        this.code = code;
    }

    public String getStatusName() {
        return this.name;
    }

    public void setStatusName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "id=" + id +
                ", typeCode='" + code + '\'' +
                ", typeName='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ABaseMoikaStatusReference that = (ABaseMoikaStatusReference) o;
        return id == that.id &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
