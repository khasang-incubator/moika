package io.khasang.moika.dao;

import io.khasang.moika.entity.WashService;

/**
 * Интерфейс DAO для сервиса мойки
 * @author Skvortsov Pavel
 *
 */
public interface WashServiceDao extends MoikaServiceDao<WashService> {

    WashService getWashServiceByIdAndCarType(long idService, int idCarType);
    WashService getWashServiceByIdAndCarType(long idService, String carTypeCode);
}
