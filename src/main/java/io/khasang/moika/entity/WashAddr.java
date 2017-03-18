package io.khasang.moika.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.math.BigDecimal;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity()
@Table(name = "addr")
public class WashAddr extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_addr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "letter")
    private String letter;

    @Column(name = "latitude")
    private BigDecimal latitude = new BigDecimal("0.0").setScale(5);

    @Column(name = "longitude")
    private BigDecimal longitude = new BigDecimal("0.0").setScale(5);

    public WashAddr() {
    }

    public WashAddr(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "WashAddr{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", letter='" + letter + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashAddr)) return false;

        WashAddr washAddr = (WashAddr) o;

        if (getId() != washAddr.getId()) return false;
        if (!getLatitude().equals(washAddr.getLatitude())) return false;
        return getLongitude().equals(washAddr.getLongitude());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getLatitude().hashCode();
        result = 31 * result + getLongitude().hashCode();
        return result;
    }

}