package io.khasang.moika.dao;

/**
 * Базовый класс исключений DAO мойки
 *
 * @author Skvortsov Pavel
 */
public class MoikaDaoException extends RuntimeException {

    public MoikaDaoException(String text) {
        super(text);
    }

    public MoikaDaoException(String text, Exception innerEx) {
        super(text, innerEx);
    }
}
