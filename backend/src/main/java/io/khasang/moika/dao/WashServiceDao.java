package io.khasang.moika.dao;

import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.WashService;

import java.util.List;

/**
 * Интерфейс DAO для сервиса мойки
 * @author Skvortsov Pavel
 *
 */
public interface WashServiceDao extends MoikaServiceDao<WashService> {

    List<WashService> getWashServices(int idFclt) throws MoikaDaoException;
    List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException;
    List<WashService> getWashServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup);
    List<WashService> getWashServiceByServiceGroup(int idFclt, String groupCode);
    WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, int idCarType);
    WashService getWashServiceByGroupAndCarType(int idFclt,  int idGroup, String carTypeCode);
}
