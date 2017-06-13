package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

/**
 * Базовый абстрактный класс для всех таблиц типов xxx_type
 * обязательные поля: id_type, code, name, descr
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABaseMoikaTypeReference extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    @Column(name = "type_code", unique = true, nullable = false)
    @NaturalId
    protected String code;
    @Column(name = "type_name")
    protected String name;
    @Column(name = "descr")
    protected String description;

    public ABaseMoikaTypeReference() {
    }

    public ABaseMoikaTypeReference(String code) {
        this.code = code;
    }

    public ABaseMoikaTypeReference(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getTypeCode() {
        return this.code;
    }

    public void setTypeCode(String code) {
        this.code = code;
    }

    public String getTypeName() {
        return this.name;
    }

    public void setTypeName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "id=" + id +
                ", typeCode='" + code + '\'' +
                ", typeName='" + name + '\'' +
                ((description != null) ? ", description='" + description : "") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ABaseMoikaTypeReference that = (ABaseMoikaTypeReference) o;
        return id == that.id &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
