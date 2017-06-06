package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.dao.CityDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.service.CarDataAccessService;
import io.khasang.moika.service.WashAddrDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("WashAddrDataAccessService")
@Transactional
public class WashAddrDataAccessServiceImpl implements WashAddrDataAccessService {
    @Autowired
    private WashAddrDao addrDao;

    @Autowired
    private CityDao cityDao;


    public WashAddrDataAccessServiceImpl() {
    }

    @Override
    public WashAddr addWashAddr(WashAddr addr) {
        //TODO Сделать проверку адреса на отсутствие дублирования
        return addrDao.create(addr);
    }

    @Override
    public WashAddr getWashAddrById(int id) {
        return addrDao.get(id);
    }

    @Override
    public List<WashAddr> getWashAddrListInCity(int cityId) {
        return addrDao.getWashAddrListInCity(cityId);
    }

    @Override
    public List<WashAddr> getAllWashAddr() {
        return addrDao.getAll();
    }

    @Override
    public boolean deleteWashAddr(int id) {
        WashAddr addrToDelete = getWashAddrById(id);
        if (addrToDelete != null) {
            try {
                addrDao.delete(addrToDelete);
                return true;
            } catch (MoikaDaoException e) {
                return false;
            }
        } else
            return false;
    }

    @Override
    public WashAddr updateWashAddr(WashAddr addr) {
        return addrDao.update(addr);
    }

    @Override
    public List<City> getCityList() {
        return cityDao.getAll();
    }

    @Override
    public City getCityByAddrId(int addrId) {
        return null;
    }

    @Override
    public WashAddr getAddrByCoord(Coordinate coord) {
        //TODO доделать получение адреса по координатам
        return null;
    }

    @Override
    public City addCity(City city) {
        //TODO Сделать проверку городла на отсутствие дублирования
        return cityDao.create(city);
    }

    @Override
    public boolean deleteCity(int id) {
        City city = cityDao.get(id);
        if (city != null) {
            try {
                cityDao.delete(city);
                return true;
            } catch (MoikaDaoException e) {
                return false;
            }
        } else
            return false;
    }

    @Override
    public City getCityByName(String cityName) {
        return cityDao.getByName(cityName);
    }


    @Override
    public City getCityById(int id) {
        return cityDao.get(id);
    }

    @Override
    public City updateCity(City city) {
        return cityDao.update(city);
    }
}
