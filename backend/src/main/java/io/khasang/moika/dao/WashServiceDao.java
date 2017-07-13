package io.khasang.moika.dao;

import io.khasang.moika.entity.WashService;

import java.util.List;

/**
 * Интерфейс DAO для сервиса мойки
 * @author Skvortsov Pavel
 *
 */
public interface WashServiceDao extends MoikaServiceDao<WashService> {

    List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException;
    WashService getWashServiceByIdAndCarType(long idService, int idCarType);
    WashService getWashServiceByIdAndCarType(long idService, String carTypeCode);
}
