package io.khasang.moika.model;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import javafx.util.Pair;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;

/**
 * <p>Интерфейс для доступа и манипулирования с данными таблицы cars в БД</p>
 */
public interface AKovalevDataAccess {
    /**
     * <p>Метод эмитирует запрос на вывод листа скидок для автомобилей определенной модели или модельного ряда</p>
     * <p>В данном случае ничего не принимает, в самой функции уже указаны проверочные значения</p>
     *
     * @return Возвращает лист пар List<Pair<String,Integer>> где String - это номер автомобиля,
     * а Integer - это скидка в процентах
     */
    List<Pair<String, Integer>> withCaseQuery();

    /**
     * <p>Метод эмитирует запрос на вывод листа скидок для автомобилей определенной модели или модельного ряда</p>
     *
     * @param params - это пары - где Sting значение в фомате SQL match expression с помощью которой указывается
     *               имя модели, модельного ряда или иные приметы для скидки, в Integer значение указывает
     *               на размер скидки в процентах
     * @return Возвращает лист пар List<Pair<String,Integer>> где String - это номер автомобиля,
     * а Integer - это скидка в процентах
     */
    List<Pair<String, Integer>> withCaseQuery(Map<String, Integer> params);

    /**
     * <p>Метод проверяет на существование записи о Car-объекте в БД по указанному в параметрах id</p>
     *
     * @param id- целочисленное значение, соответсвующее полю id в таблице cars в БД
     * @return boolean - Возвращает true, если объект найден и false, ексли отсутствует
     */
    boolean containsCarQuery(long id);

    /**
     * <p>Метод возвращает список всех Car-объектов существующих в БД</p>
     *
     * @return List<Car> - список объектов найденных в БД
     */
    List<Car> selectAllCarsQuery();

    /**
     * <p>Метод выводит Car-объект соответсвующие указанному в параметрах id</p>
     *
     * @param id- целочисленное значение, соответсвующее полю id в таблице cars в БД
     * @return Car - объект найденный в БД
     */
    Car selectQuery(long id);

    /**
     * <p>Метод выводит список Car-объектов соответсвующие указанному в параметрах типу(type)</p>
     *
     * @param type - строкое значение, типа соответсвующее полю cartype в таблице cars в БД
     * @return List<Car> - список объектов найденных в БД
     */
    List<Car> selectQuery(String type);

    /**
     * <p>Метод обновляет запись в таблице cars, объект обновит соответствующую запись в таблице, если
     * car.id и id записи в БД совпадают </p>
     *
     * @param car - объект, который необходимо обновить
     */
    void updateQuery(Car car);

    /**
     * <p>Метод добавляет запись в таблицу cars, car.id объекта игнорируется,
     * метод сам автоматически подставит в это поле корректное значение</p>
     *
     * @param car - объект, который необходимо добавить
     */
    void insertQuery(Car car);

    /**
     * <p>Метод добавляет несколько записей в таблицу cars, car.id объекта игнорируется,
     * метод сам автоматически подставит в это поле корректное значение</p>
     *
     * @param cars - лист объектов, которые необходимо добавить
     */
    void insertQuery(List<Car> cars);

    /**
     * <p>Метод демонстрирует работу SQL запроса с использованием inner join, запрос позволяет выбрать пары машин
     * и клиентов у которых номера телефонов начинаются на 555</p>
     *
     * @return List<Pair<Car,Client>> - список пар найденных в БД
     */
    List<Pair<Car, Client>> joinQuery();

    /**
     * <p>Метод демонстрирует работу SQL запроса с использованием вложенного Select'а, запрос позволяет выбрать машины
     * у которых номер региона равен 777</p>
     *
     * @return List<Car> - список car-объектов найденных в БД
     */
    List<Car> withinSelectQuery();

    /**
     * <p>Метод удаляет объект из БД</p>
     *
     * @param id - объекта, который необходимо удалить
     */
    void deleteQuery(Long id);

    /**
     * <p>Метод удаляет объект из БД</p>
     *
     * @param carnumber - объекта, который необходимо удалить
     */
    void deleteQuery(String carnumber);

    /**
     * <p>Метод удаляет все объекты car из таблице car с помощью SQL-перации TRUNCATE</p>
     */
    void truncateQuery();

    /**
     * <p>Делает backup таблицы cars </p>
     * <p>Результат выполнения утилиты pg_dump пишется в лог</p>
     *
     * @param environment - должен содержать следуюзие свойства(properties):
     *                    jdbc.postgresql.pgDump.path - полный путь к утелите pg_dump
     *                    jdbc.postgresql.pgDump.url - url postgres сервера с указанием в нем пароля, если он необходим
     *                    jdbc.postgresql.pgDump.backupFolder - полнуй путь дериктории где будет хранится бэкап-файл
     *                    jdbc.postgresql.pgDump.backupName - имя бэкап-файла с указанием расширения
     *                    jdbc.postgresql.username - имя пользователя для доступа к БД
     *                    Пример:
     *                    jdbc.postgresql.pgDump.path=c:/Program Files/PostgreSQL/9.5/bin/
     *                    jdbc.postgresql.pgDump.url =postgres://postgres:root@localhost/carwash
     *                    jdbc.postgresql.pgDump.backupFolder=c:/Program Files/PostgreSQL/9.5/bin/
     *                    jdbc.postgresql.pgDump.backupName=tb.sql
     *                    jdbc.postgresql.username=root
     */
    void doBackup(Environment environment);
}
