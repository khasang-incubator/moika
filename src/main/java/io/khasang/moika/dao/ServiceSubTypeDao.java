package io.khasang.moika.dao;

import io.khasang.moika.entity.ServiceSubType;
import io.khasang.moika.entity.ServiceType;

import java.util.List;

/**
 * Интерфейс DAO для типов видов автомойки
 * @author Skvortsov Pavel
 *
 */
public interface ServiceSubTypeDao extends BaseMoikaTypeDao<ServiceType> {

    List<ServiceSubType> getServiceSubTypeBybaseType(String typeCode) throws MoikaDaoException;;
    List getServiceSubTypeBybaseType(int idType) throws MoikaDaoException;;

}
