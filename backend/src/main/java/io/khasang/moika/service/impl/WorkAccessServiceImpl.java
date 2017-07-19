package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("WorkAccessServiceImpl")
@Transactional
public class WorkAccessServiceImpl implements WorkAccessService {
    @Qualifier("workDaoImpl")
    @Autowired
    WorkDao workDao;

    public WorkAccessServiceImpl() {
    }

    @Override
    public Work createWork(Work work) throws MoikaDaoException {
        return workDao.create(work);
    }

    @Override
    public Work getWork(long id) throws MoikaDaoException {
        return workDao.get(id);
    }

    @Override
    public Work updateWork(Work work) throws MoikaDaoException {
        return workDao.update(work);
    }

    @Override
    public boolean deleteWork(Work work) throws MoikaDaoException {
        Work workToDelete = getWork(work.getIdWork());
        if (workToDelete != null) {
            try {
                workDao.delete(workToDelete);
                return true;
            } catch (MoikaDaoException e) {
                return false;
            }
        } else
            return false;
    }

    @Override
    public List<Work> getAllWorks(int idFclt) {
        return workDao.getWorksInFacility( idFclt);
    }
}
