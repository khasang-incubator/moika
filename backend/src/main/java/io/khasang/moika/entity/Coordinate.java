package io.khasang.moika.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Сущность, описывающая географические координаты, используется для Гео сервисов поиска моек
 */
@Embeddable
public class Coordinate {
    private static final Logger logger = LoggerFactory.getLogger(Coordinate.class);
    @Column(name = "latitude")
    private BigDecimal latitude = new BigDecimal("0.0").setScale(5);

    @Column(name = "longitude")
    private BigDecimal longitude = new BigDecimal("0.0").setScale(5);

    public Coordinate(){
    }

    public Coordinate(BigDecimal lat, BigDecimal lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    public Coordinate(String lat, String lon){
        try {
            this.latitude = new BigDecimal(lat).setScale(5);
            this.longitude = new BigDecimal(lon).setScale(5);
        }
        catch (NumberFormatException e){
            this.latitude = new BigDecimal("0.0").setScale(5);
            this.longitude = new BigDecimal("0.0").setScale(5);
            logger.error("Incorrect coordinate initialization!!! lat:"+lat +" : " + "lon:"+lon );
        }
    }


    public BigDecimal getLat() {
        return latitude;
    }

    public void setLat(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLon() {
        return longitude;
    }

    public void setLon(BigDecimal longitude) {
        this.longitude = longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (!latitude.equals(that.latitude)) return false;
        return longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        int result = latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }
}
