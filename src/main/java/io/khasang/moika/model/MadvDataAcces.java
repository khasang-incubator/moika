package io.khasang.moika.model;

import java.util.List;

public interface MadvDataAcces {
    List<String> select(String tableName);
    List<String> select(String tableName, String condition, Object[] args);
    String insert(String tableName, Object[] args );
    String update(String tableName, String[] fileds, String cond, Object[] args);
    String create(String tableName, String[] fields, String[] fieldsTypes, String constraint);
    List<String> join(String tableName1, String tableName2, String joinfield, String condition, Object[] args);
    List<String> internalRequest(String tableName1, String tableName2, String inField, String wherefield, String condition, Object[] args);
    List<String> caseSelect(String tableName, String field, String caseWhen, String caseThen, String caseElse, String condition, Object[] args);
    String truncate(String table);
    String backupDB(String fileName);


}
