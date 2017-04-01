package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaStatusReference;

/**
 * Базовый интерфейс DAO для всех справрчников статусов чего-либо
 * @author Skvortsov Pavel
 *
 */

public interface BaseMoikaStatusDao<T extends ABaseMoikaStatusReference> extends IMoikaDaoCrud<T> {
      T getEntityByCode(String code) throws MoikaDaoException;
}
