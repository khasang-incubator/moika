package io.khasang.moika.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface RostislavDataAccessService {

    /**
     * Получить сведения обо всех машинах.
     *
     * @return
     */
    List<Map<String, Object>> getAllCars();

    /**
     * Получить сведения машинах определённого типа.
     *
     * @param carType тип машины
     * @return
     */
    List<Map<String, Object>> getCars(String carType);
}
