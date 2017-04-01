package io.khasang.moika.service.impl;

import io.khasang.moika.model.MadvDataAcces;
import io.khasang.moika.service.MadvDataAccesService;

import java.util.List;

/**
 * Created by madv on 20.02.2017.
 */
public class MadvDataAccesServiceImpl implements MadvDataAccesService {
    private MadvDataAcces madvDataAcces;

    public MadvDataAccesServiceImpl() {
    }

    public MadvDataAccesServiceImpl(MadvDataAcces madvDataAcces) {
        this.madvDataAcces = madvDataAcces;
    }

    public MadvDataAcces getMadvDataAcces() {
        return madvDataAcces;
    }

    public void setMadvDataAcces(MadvDataAcces madvDataAcces) {
        this.madvDataAcces = madvDataAcces;
    }

    @Override
    public String test() {
        return "Попытка удалить страницу " + madvDataAcces.truncate("cars");
    }

    @Override
    public String createDogs() {
        String fields[] = {"id", "name", "age"};
        String typeFilds[] = {"serial", "varchar (20)", "integer"};
        madvDataAcces.create("dogs", fields, typeFilds, " id_pkey PRIMARY KEY (id)");
        return "Создана таблица Dogs";
    }

    @Override
    public List<String> selectAllDogs() {
        return madvDataAcces.select("dogs");
    }
}
