package io.khasang.moika.model.impl;

import io.khasang.moika.model.MadvDataAcces;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MadvDataAccesImpl implements MadvDataAcces {
    private JdbcTemplate jdbcTemplate;

    public MadvDataAccesImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MadvDataAccesImpl() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> select(String tableName) {
        final String sqlStr = "select * from " + tableName;
        return getSelectedRows(sqlStr, null);
    }
    @Override
    public List<String> select(String tableName, String condition, Object[] args) {
        final String sqlStr = "select * from " + tableName + " where " + condition;
        return getSelectedRows(sqlStr, args);
    }
    @Override
    public String insert(String tableName, Object[] args) {
        StringBuilder sqlStr = new StringBuilder("insert into " + tableName + " ( ");
        for (int i = 0; i < args.length; i++) {
            sqlStr.append((i == 0) ? "?" : ", ? ");
        }
        sqlStr.append(")");
        int rows = jdbcTemplate.update(sqlStr.toString(), args);
        return rows + " rows inserted!";
    }
    @Override
    public String update(String tableName, String[] fileds, String cond, Object[] args) {

        StringBuilder sqlStr = new StringBuilder("update " + tableName + " set ");
        for (int i = 0; i < fileds.length; i++) {
            sqlStr.append(((i == 0) ? "" : ",") + fileds[i] + " = ? ");
        }
        if (cond.length() > 0) {
            sqlStr.append(" where " + cond);
        }
        int rows = jdbcTemplate.update(sqlStr.toString(), args);

        return rows + " rows updated!";
    }
    @Override
    public String truncate(String table) {
        try {
            jdbcTemplate.execute("TRUNCATE "+table);
            return "Таблица " + table+ " успешно очищена";
        } catch (DataAccessException e) {
           return "Ошибка при очистке теблицы"+ table+ ": "+e;
        }
    }
    @Override
    public String create(String tableName, String[] fields, String[] fieldsTypes, String constraint) {
        if (fields.length > 0) {
            StringBuilder sqlStr = new StringBuilder("DROP TABLE IF EXISTS " + tableName + ";\n");
            sqlStr.append("CREATE TABLE " + tableName + "(\n");
            for (int i = 0; i < fields.length; i++) {
                sqlStr.append(((i == 0) ? "" : ",") + fields[i] + "  " + fieldsTypes[i] + "\n");
            }
            if (constraint != null) {
                sqlStr.append(", CONSTRAINT " + constraint + "\n");
            }
            sqlStr.append(");\n");

            try {
                jdbcTemplate.execute(sqlStr.toString());
                return sqlStr.toString() + "Table " + tableName + " created!";
            } catch (Exception e) {
                return sqlStr.toString() + "Table creation failed: " + e;
            }
        } else
            return "You should define fields!";
    }
    @Override
    public List<String> join(String tableName1, String tableName2, String joinfield, String condition, Object[] args) {
        final String sqlStr = "select * from " + tableName1 + "inner join " + tableName1 + "on " + joinfield + " where " + condition;
        return getSelectedRows(sqlStr, args);
    }

    @Override
    public List<String> internalRequest(String tableName1, String tableName2, String inField, String wherefield, String condition, Object[] args) {
        StringBuilder sqlStr = new StringBuilder("select * from " + tableName1 + " " + " where  " + inField + " IN ");
        sqlStr.append("(select " + inField + "  from " + tableName2 + ") ");
        sqlStr.append(" where " + condition);
        return getSelectedRows(sqlStr.toString(), args);
    }

    @Override
    public List<String> caseSelect(String tableName, String field, String caseWhen, String caseThen, String caseElse, String condition, Object[] args) {
        StringBuilder sqlStr = new StringBuilder("select (case when ");
        sqlStr.append(caseWhen );
        sqlStr.append(" then "+caseThen );
        sqlStr.append( (caseElse != null) ? " else  " + caseElse : "");
        sqlStr.append( " end )");
        sqlStr.append(" from "+tableName);
        sqlStr.append((condition != null ) ? " where " + condition + ";" : ";");
        return getSelectedRows(sqlStr.toString(), args);
    }

    @Override
    public String backupDB(String fileName) {
        String res = null;
        try {
            res = "pgdump " + jdbcTemplate.getDataSource().getConnection().getCatalog() + " > " + fileName;
        } catch (SQLException e) {
            res = e.getLocalizedMessage();
        }
        return res;
    }

    private List<String> getSelectedRows(String sqlStr, Object[] args) {
        List<Map<String, Object>> rows = new ArrayList<>();
        List<String> strings = new ArrayList<String>();
        String sqlQuery = sqlStr; // "Select * from " + tableName;
        try {
            if (args != null)
                rows = jdbcTemplate.queryForList(sqlQuery, args);
            else
                rows = jdbcTemplate.queryForList(sqlQuery);
            if (rows.size() > 0) {
                StringBuilder strb = new StringBuilder();
                for (Map.Entry<String, Object> entry : rows.get(0).entrySet()) strb.append(entry.getKey() + " | ");
                strings.add(strb.toString());
                //int i = 1;
                //for (Map<String, Object> fields : rows) {
                for (int i = 0; i < rows.size(); i++) { //прошлись по записям
                    strb.setLength(0);
                    Iterator entries = rows.get(i).entrySet().iterator();
                    while (entries.hasNext()) { //прошлись по полям
                        Map.Entry thisEntry = (Map.Entry) entries.next();
                        Object value = thisEntry.getValue();
                        if (value != null)
                            strb.append(value.toString() + " | ");
                        else
                            strb.append(" null | ");
                    }
                    strings.add(strb.toString());
                }
            } else strings.add("Table is empty!");
        } catch (Exception e) {
            strings.add(e.getMessage());
        } finally {
            return strings;
        }
    }
}

