package io.khasang.moika.dao;

import io.khasang.moika.entity.EServiceGroup;
import io.khasang.moika.entity.EServiceType;
import io.khasang.moika.entity.MoikaService;

import java.util.List;


/**
 * Базовый интерфейс DAO для всех услуг
 * @author Skvortsov Pavel
 *
 */

public interface MoikaServiceDao<T extends MoikaService> extends IMoikaDaoCrud<T>{
    List<T> getServicesByType(int idFclt, EServiceType serviceType) throws MoikaDaoException;
    List<T> getServicesByType(int idFclt, String typeCode) throws MoikaDaoException;
    List<T> getServices(int idFclt) throws MoikaDaoException;
    List<T> getServicesByGroup(int idFclt, EServiceGroup serviceGroup) throws MoikaDaoException;
    List<T> getServicesByGroup(int idFclt, String groupCode) throws MoikaDaoException;
    List<T> getServicesByStatus(int idFclt, String statusCode) throws MoikaDaoException;
    List<T> getServicesByStatus(int idFclt, int idStatus) throws MoikaDaoException;
    List<T> getActualServices(int idFclt ) throws MoikaDaoException;

}
