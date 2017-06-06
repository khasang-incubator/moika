package io.khasang.moika.dao;

import io.khasang.moika.entity.Work;

import java.util.Date;
import java.util.List;

public interface WorkDao extends IMoikaDaoCrud<Work> {
    List<Work> getWorksByIdOrder(long idOrder);
    Work getWorkInBox(long idWashBox);
    Work getWorkInBox(long idWashBox, Date workDateAndTime);
    List<Work> getWorksInFacility(int idFclt);
    List<Work> getWorksInFacility(int idFclt, Date workDateAndTime);
}

