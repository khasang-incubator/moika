package io.khasang.moika.model;

import java.util.List;

public interface PskvorDataAccess {
    /**
     * Чтение всех строк из таблицы
     * @param tableName имя таблицы
     * @return - массив строк, содержащих записи таблицы, с разделителем "|"
     * Первая строка массива - наименование колонок
     */
    List<String> readData(String tableName);

    /**
     * Чтение всех строк из таблицы
     * @param tableName имя таблицы
     * @param cond условие (без фразы where, значения подставляются на место ? ( например  "name = ? and date = ?")
     * @param args - массив значений условий
     * @return  массив строк, содержащих записи таблицы, с разделителем "|"
     * Первая строка массива - наименование колонок
     */
    List<String> readData(String tableName, String cond, Object[] args);

    /**
     * Добавление строки в таблицу, где
     * @param tableName имя таблицы
     * @param args - значения полей
     * @return - "?" rows inserted
     */
    String addData(String tableName, Object[] args );

    String addDataReturnId(String tableName, Object[] args );

    /**
     * Изменяет данные в таблице, где
     * @param tableName имя таблицы
     * @param fileds массив изменяемых полей
     * @param cond условие изменеия без фразы where, значения подставляются на место ? ( например  "name = ? and date = ?")
     * @param args  массив подстановочных значений. Массив включает как изменяемые значения, так и условия в порядке следования значений в массивах fields и args
     * @return количество измененых строк или описание ошибки
     */
    String changeData(String tableName, String[] fileds, String cond, Object[] args);

    /**
     * Создание таблицы, где
     * @param tableName - имя таблицы
     * @param fields - поля таблицы
     * @param fieldsTypes - типы полей
     * @param constraint - ключи и ограничения, без оператора CONDTRAINT
     * @return - успех создания или описание ошибки
     */
    String createData(String tableName, String[] fields, String[] fieldsTypes, String constraint);

    /**
     * Удаление данных из таблицв, где
     * @param tableName - имя таблицы
     * @param cond - условие изменеия без фразы where, значения подставляются на место ? ( например  "name = ? and date = ?")
     * @param args - массив подстановочных значений.
     * @return - количество удаленных строк или описание ошибии
     */
    String deleteData(String tableName, String cond, Object[] args);

    /**
     * Чтение массив строк, содержащих выборку из двух таблиц, объединенных через INNER JOIN
     * @param tableNames   список объединяемыъ таблиц
     * @param joinFields Список из массива элементов["fieldA", "fieldB"] для объединения fieldA = fieldB
     * @param condition словие изменеия без фразы where, значения подставляются на место ? ( например  "name = ? and date = ?")
     * @param args - массив подстановочных значений.
     * @return количество удаленных строк или описание ошиби
     */
    List<String> joinData(List<String> tableNames, List<String[]> joinFields, String condition, Object[] args);
    List<String> innerSelectData(String tableName1, String tableName2, String inField, String wherefield, String condition, Object[] args);
    List<String> caseSelectData(String tableName, String field, String caseWhen, String caseThen, String caseElse, String condition, Object[] args);

    /**
     * Выполняет резервное копирование БД в
     * @param pgDumpPath путь к pd_dump
     * @param fileName файл р.к.
     * @param isExecute выполняет комманду или тольк выводит ее
     * @return  результат выполнения операции архивации
     */
    String backupData(String pgDumpPath, String fileName, boolean isExecute);
}
