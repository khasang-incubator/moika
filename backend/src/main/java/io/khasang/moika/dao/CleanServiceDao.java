package io.khasang.moika.dao;

import io.khasang.moika.entity.CleanService;
import io.khasang.moika.entity.EServiceGroup;

import java.util.List;

/**
 * Интерфейс DAO для услуг чиски салона
 * @author Skvortsov Pavel
 *
 */
public interface CleanServiceDao extends MoikaServiceDao<CleanService> {

    List<CleanService> getCleanServices(int idFclt) throws MoikaDaoException;
    List<CleanService> getServicesByDirtType(int idFclt, String dirtTypeCode) throws MoikaDaoException;
    List<CleanService> getCleanServiceByServiceGroup(int idFclt, EServiceGroup serviceGroup);
    List<CleanService> getCleanServiceByServiceGroup(int idFclt, String groupCode);
    CleanService getCleanServiceByGroupAndDirtType(int idFclt, int idGroup, int idDirtType);
    CleanService geCleanServiceByGroupAndDirtType(int idFclt,  int idGroup, String dirtTypeCode);
}
