package io.khasang.moika.dao;


import io.khasang.moika.entity.WashBox;

import java.util.List;
/**
 * Интерфейс DAO для боксов автомоек
 * @author Skvortsov Pavel
 *
 */
public interface WashBoxDao extends IMoikaDaoCrud<WashBox>{
    WashBox getWashBoxByName(int idFacility, String name ) throws MoikaDaoException;
    List<WashBox> getWashBoxesOnFacility(int idFacility) throws MoikaDaoException;
    List<WashBox> getWashBoxesByType(int boxType) throws MoikaDaoException;
    List<WashBox> getWashBoxesByStatus(int boxStatus) throws MoikaDaoException;
}
