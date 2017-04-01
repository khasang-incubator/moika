package io.khasang.moika.service.impl;

import io.khasang.moika.model.RostislavDataAccess;
import io.khasang.moika.service.RostislavDataAccessService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RostislavDataAccessServiceImpl implements RostislavDataAccessService {

    RostislavDataAccess rostislavDataAccess;

    public RostislavDataAccessServiceImpl() {
    }

    @Override
    public List<Map<String, Object>> getAllCars() {
        return rostislavDataAccess.getSelectedDataFromDbEntity("Cars", null);
    }

    @Override
    public List<Map<String, Object>> getCars(String carType) {

        Map<String, Pair<String, String>> filter = new HashMap<>();
        filter.put("carmodel", new Pair<>("=", carType));

        return rostislavDataAccess.getSelectedDataFromDbEntity("Cars", filter);
    }
}
