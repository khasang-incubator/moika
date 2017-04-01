package io.khasang.moika.service.impl;

import io.khasang.moika.model.PskvorDataAccess;
import io.khasang.moika.service.PskvorDataAccessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PskvorDataAccessServiceImpl implements PskvorDataAccessService {
    PskvorDataAccess pskvorDataAccess;

    public PskvorDataAccessServiceImpl() {}

    public PskvorDataAccessServiceImpl(PskvorDataAccess pskvorDataAccess) {
        this.pskvorDataAccess = pskvorDataAccess;
    }

    private String getRowsAsString(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
            sb.append("<br>");
        }
        sb.append(strings.size() - 1 + " rows selected <br>");
        return sb.toString();
    }

    public PskvorDataAccess getPskvorDataAccess() {
        return pskvorDataAccess;
    }

    public void setPskvorDataAccess(PskvorDataAccess pskvorDataAccess) {
        this.pskvorDataAccess = pskvorDataAccess;
    }

    @Override
    public String select(String tableName) {
        return getRowsAsString(pskvorDataAccess.readData(tableName));
    }

    @Override
    public String select(String tableName, String cond, Object[] args) {
        return getRowsAsString(pskvorDataAccess.readData(tableName, cond, args));
    }

    @Override
    public String createData(String tableName, String[] fields, String[] fieldsTypes, String constraint) {
        return pskvorDataAccess.createData(tableName, fields, fieldsTypes, constraint);
    }

    @Override
    public String changeData(String tableName, String[] fields, String cond, Object[] args) {
        return pskvorDataAccess.changeData(tableName, fields, cond, args);
    }

    @Override
    public String addData(String tableName, Object[] args){
        return pskvorDataAccess.addData(tableName, args);
    }

    @Override
    public String joinSelect(List<String> tableNames, List<String[]> joinFields, String condition, Object[] args){
        return getRowsAsString(pskvorDataAccess.joinData(tableNames, joinFields, condition, args));
    }

    @Override
    public String deleteData(String tableName, String cond, Object[] args) {
        return pskvorDataAccess.deleteData(tableName, cond, args);
    }

    @Override
    public String backupData(String pgDumpPath, String fileName, boolean isExecute) {
        return pskvorDataAccess.backupData(pgDumpPath, fileName, false);
    }
}
