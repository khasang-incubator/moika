package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name= "cities")
public class City extends ABaseMoikaEntity {
    @Id
    @Column(name = "id_city", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column
    private String region;


    public City() {
    }

    public City(String cityName) {
        this.name = cityName;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                "region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (getId() != city.getId()) return false;
        return getName() != null ? getName().equals(city.getName()) : city.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}