package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.Work;

import java.util.List;

public interface WorkAccessService  {
    Work createWork(Work work) throws MoikaDaoException;
    Work getWork(long id) throws MoikaDaoException;
    Work updateWork(Work work) throws MoikaDaoException;
    boolean deleteWork(Work work) throws MoikaDaoException;
    List<Work> getAllWorks(int idFclt) throws MoikaDaoException;
}
