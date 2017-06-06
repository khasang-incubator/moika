package io.khasang.moika.dao;

import io.khasang.moika.entity.MoikaService;

import java.util.List;


/**
 * Базовый интерфейс DAO для всех услуг
 * @author Skvortsov Pavel
 *
 */

public interface MoikaServiceDao<T extends MoikaService> extends IMoikaDaoCrud<T>{
    List<T> getServicesByType(String typeCode) throws MoikaDaoException;
    List<T> getServicesBySubType(String subTypeCode) throws MoikaDaoException;
    List<T> getServicesByStatus(String statusCode) throws MoikaDaoException;
    List<T> getServicesByStatus(int idStatus) throws MoikaDaoException;
    List<T> getActualServices() throws MoikaDaoException;
}
