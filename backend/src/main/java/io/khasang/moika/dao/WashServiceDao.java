package io.khasang.moika.dao;

import io.khasang.moika.entity.WashService;

/**
 * Интерфейс DAO для сервиса мойки
 * @author Skvortsov Pavel
 *
 */
public interface WashServiceDao  extends BaseMoikaConcreatServiceDao<WashService> {

    WashService getWashServiceByIdAndType(long idService, int idCarType);
    WashService getWashServiceByIdAndType(long idService, String carTypeCode);
}
