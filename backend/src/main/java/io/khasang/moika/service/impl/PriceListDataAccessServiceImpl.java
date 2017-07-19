package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.PriceListDao;
import io.khasang.moika.entity.PriceList;
import io.khasang.moika.service.PriceListDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pauls on 19.07.2017.
 */
@Service(value = "priceListDataAccessService")
@Transactional
public class PriceListDataAccessServiceImpl implements PriceListDataAccessService {
    @Autowired
    PriceListDao priceListDao;

    @Override
    public PriceList getPriceListByServiceType(int idFclt, int serviceType) throws MoikaDaoException {
        return priceListDao.getPriceListByServiceType(idFclt, serviceType);
    }

    @Override
    public List<PriceList> getFullPriceList(int idFclt) throws MoikaDaoException {
        return priceListDao.getFullPriceList(idFclt);
    }
}
