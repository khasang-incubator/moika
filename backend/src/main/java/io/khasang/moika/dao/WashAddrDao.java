package io.khasang.moika.dao;

import io.khasang.moika.entity.WashAddr;

import java.util.List;

/**
 * Интерфейс DAO для адресов моек
 * @author Skvortsov Pavel
 *
 */
public interface WashAddrDao extends IMoikaDaoCrud<WashAddr> {

    List<WashAddr> getWashAddrListInCity(int cityId);
}
