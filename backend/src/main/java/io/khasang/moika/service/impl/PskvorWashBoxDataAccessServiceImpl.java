package io.khasang.moika.service.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.PskvorWashBoxDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("pskvorWashBoxDaoServiceImpl")
@Transactional
public class PskvorWashBoxDataAccessServiceImpl implements PskvorWashBoxDataAccessService {
    @Autowired
    private WashBoxDao washBoxDao;

    public PskvorWashBoxDataAccessServiceImpl() {
    }

    @Override
    public WashBox addWashBox(WashBox washBox) {
        WashBox resBox = null;
        try {
            resBox =  washBoxDao.create(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return resBox;
    }

    @Override
    public WashBox updateWashBox(WashBox washBox) {
        WashBox resBox = null;
        try {
            resBox = washBoxDao.update(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return resBox;
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        try {
            washBoxDao.delete(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WashBox getWashBoxById(int id) {
        try {
            return washBoxDao.get(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WashBox getWashBoxByName(int idFclt, String name) {
        try {
            return washBoxDao.getWashBoxByName(idFclt, name);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByType(int boxType) {
        try {
            return washBoxDao.getWashBoxesByType(boxType);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByStatus(int boxStatus) {
        try {
            return washBoxDao.getWashBoxesByStatus(boxStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        try {
            return washBoxDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        try {
            return washBoxDao.getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }


/*
    @Override
    public List<BoxStatus> getWashBoxesStatuses() {
        try {
            return boxStatusDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BoxStatus getWashBoxesStatusByCode(String code) {
        try {
            return (BoxStatus) boxStatusDao.getEntityByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BoxType> getWashBoxesTypes() {
        try {
            return boxTypeDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public BoxType getWashBoxesTypeByCode(String code) {
        try {
            return (BoxType) boxTypeDao.getEntityByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    */
}