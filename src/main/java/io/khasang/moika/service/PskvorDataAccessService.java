package io.khasang.moika.service;

import io.khasang.moika.model.PskvorDataAccess;

import java.util.List;


public interface PskvorDataAccessService {
    String select(String tableName);
    String select(String tableName, String cond, Object[] args);
    String createData(String tableName, String[] fields, String[] fieldsTypes, String constraint);
    String changeData(String tableName, String[] fields, String cond, Object[] args);
    String addData(String tableName, Object[] args);
    String joinSelect(List<String> tableNames, List<String[]> joinFields, String condition, Object[] args);
    String deleteData(String tableName, String cond, Object[] args);
    String backupData(String pgDumpPath, String fileName, boolean isExecute);


}
