package io.khasang.moika.model.impl;

import io.khasang.moika.model.RostislavDataAccess;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RostislavDataAccessImpl implements RostislavDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RostislavDataAccessImpl() {
    }

    @Override
    public List<Map<String, Object>> getSelectedDataFromDbEntity(String tableName, Map<String, Pair<String, String>> filters) {

        final StringBuilder sqlQueryBuilder = new StringBuilder("select * from ").append(tableName);
        final ArrayList<String> filterValues = new ArrayList<>();

        if (filters != null && !filters.isEmpty()) {
            sqlQueryBuilder.append(" where ");

            String where = filters.entrySet().stream()
                    .map(condition -> {
                        filterValues.add(condition.getValue().getValue());
                        return condition.getKey() + " " + condition.getValue().getKey() + " ?";
                    })
                    .collect(Collectors.joining(" and "));

            sqlQueryBuilder.append(where);

        }

        if(!filterValues.isEmpty()) {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString(), filterValues.toArray());
        } else{
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString());
        }

    }

    @Override
    public List<Map<String, Object>> getSelectedDataFromJoinedDbEntities(String leftTableName, String rightTableName, Map<String, Pair<String, String>> joinType, Map<String, Pair<String, String>> filters) {
        //TODO: реализовать метод
        throw new RuntimeException("Mетод ещё не реализован!");
    }
}
