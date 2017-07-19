package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.PriceList;

import java.util.List;

/**
 * Created by pauls on 19.07.2017.
 */
public interface PriceListDataAccessService {

    PriceList getPriceListByServiceType(int idFclt, int serviceType) throws MoikaDaoException;
    List<PriceList> getFullPriceList(int idFclt) throws MoikaDaoException;
}
