package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.WashService;

import java.util.List;

public interface WashServiceDataAccessService extends MoikaServiceDataAccessService<WashService> {

    List<WashService> getWashServices(int idFclt) throws MoikaDaoException;
    List<WashService> getServicesByCarType(int idFclt, String carTypeCode) throws MoikaDaoException;
    List<WashService> getWashServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup);
    List<WashService> getWashServiceByServiceGroup(int idFclt, String groupCode);
    WashService getWashServiceByGroupAndCarType(int idFclt, int idGroup, int idCarType);
    WashService getWashServiceByGroupAndCarType(int idFclt,  int idGroup, String carTypeCode);
}
