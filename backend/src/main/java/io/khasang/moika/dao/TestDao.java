package io.khasang.moika.dao;

import io.khasang.moika.entity.Test;

import java.util.List;

public interface TestDao {
    void addTest(Test test);
    void updateTest(Test test);
    void deleteTest(Test test);
    Test getTestByID(int id);
    List<Test> getAllTests();
    List<Test> getTestsByName(String name1, String name2);
}
