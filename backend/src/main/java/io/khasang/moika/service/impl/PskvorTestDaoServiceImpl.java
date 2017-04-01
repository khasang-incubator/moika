package io.khasang.moika.service.impl;


import io.khasang.moika.dao.TestDao;
import io.khasang.moika.entity.Test;
import io.khasang.moika.service.PskvorTestDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("PskvorTestDaoServiceImpl")
@Transactional
public class PskvorTestDaoServiceImpl implements PskvorTestDaoService{
    @Autowired
    private TestDao testDao;

    public PskvorTestDaoServiceImpl() {
    }

    @Override
    public void addTest(Test test) {
        testDao.addTest(test);
    }

    @Override
    public void updateTest(Test test) {
        testDao.updateTest(test);
    }

    @Override
    public void deleteTest(Test test) {
        testDao.deleteTest(test);
    }

    @Override
    public Test getTestByID(int id) {
        return testDao.getTestByID(id);
    }

    @Override
    public List<Test> getAllTests() {
        return testDao.getAllTests();
    }

    @Override
    public List<Test> getTestsByName(String name1, String name2) {
        return getTestsByName(name1, name2);
    }
}
