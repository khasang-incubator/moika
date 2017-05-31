package io.khasang.moika.service;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;

import java.util.List;

/**
 * Created by pauls on 31.05.2017.
 */
public interface WashAddrDataAccessService {
    WashAddr addWashAddr(WashAddr addr);

    WashAddr getWashAddrById(int id);

    List<WashAddr> getWashAddrListInCity(int cityId);

    List<WashAddr> getAllWashAddr();

    boolean deleteWashAddr(int id);

    WashAddr updateWashAddr(WashAddr addr);

    WashAddr getAddrByCoord(Coordinate coord);

    List<City> getCityList();

    City getCityByAddrId(int addrId);

    City getCityById(int id);

    City getCityByName(String cityName);

    City addCity(City city);

    boolean deleteCity(int id);

    City updateCity(City city);
}
