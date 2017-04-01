package io.khasang.moika.integration;


import io.khasang.moika.entity.User;
import io.khasang.moika.util.DataAccessUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserIntegrationTest extends Assert {

    static final Logger LOGGER = LoggerFactory.getLogger(UserIntegrationTest.class);
    static String baseUrl = "http://localhost:8080";
    DataAccessUtil dataAccessUtil = new DataAccessUtil();
    Map<String, String> tokenData;
    RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();
    @Before
    public void prereq() {
        LOGGER.debug("0. Подготовка");
        restTemplate = new RestTemplate();

        LOGGER.debug("0.1. Получение CSRF-токена");
        ResponseEntity<Map> tokenDataEntity = restTemplate.getForEntity(baseUrl + "/csrf_token", Map.class);
        tokenData = tokenDataEntity.getBody();
        assertNotNull(tokenData);
        assertNotNull(tokenData.get("token"));

        System.out.println("Header: "+ tokenData.get("headerName"));
        System.out.println("Token: "+ tokenData.get("token"));

        headers.put(tokenData.get("headerName"), Collections.singletonList(tokenData.get("token")));
    }

    @Test
    public void userCRUDTest() {
        String login = "t" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());


        User user = new User();
        HttpEntity<User> httpEntity1 = new HttpEntity<>(user, headers);
        Map<String, Object> resultMap;

        LOGGER.debug("1. Создание пользователя");
        LOGGER.debug("1.1. Предварительная валидация  - ПРОВАЛ (не задан телефон)");
        user.setLogin(login);
        user.setFirstName("Тест");
        user.setEmail(login + "@mail.ru");
        user.setPassword("123456Qw");

        resultMap = restTemplate.postForObject(baseUrl + "/users", httpEntity1, Map.class);
        assertTrue(resultMap.containsKey("errors"));
        assertTrue(resultMap.get("errors").toString().contains("phone"));


        LOGGER.debug("1.2. Предварительная валидация  - УСПЕХ");
        user.setPhone("1234567890");
        resultMap = restTemplate.postForObject(baseUrl + "/users/validation", httpEntity1, Map.class);
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("1.3. Собственно создание");
        resultMap = restTemplate.postForObject(baseUrl + "/users", httpEntity1, Map.class);
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("1.4. Проверка наличия присвоенного ID");
        Object idInfo = resultMap.get("success");
        assertTrue(idInfo != null);
        assertTrue(idInfo instanceof Number);
        Long createdUserId = ((Number) idInfo).longValue();

        LOGGER.debug("2. Чтение пользователей");
        LOGGER.debug("2.1 Проверка наличия созданного юзера с присвоенным ID в БД");
        User createdUser = restTemplate.getForObject(baseUrl + "/users/{id}", User.class, createdUserId);
        assertTrue(createdUser != null);
        assertEquals(createdUser.getLogin(), login);

        LOGGER.debug("2.2. Получение списка пользователей и проверка, что вновь созданный в нём есть");
        User[] users = restTemplate.getForObject(baseUrl + "/users", User[].class);
        assertTrue(Arrays.asList(users).stream().anyMatch(u -> u.getId() == createdUserId && u.getLogin().equals(login)));

        LOGGER.debug("3. Обновление пользователя");
        LOGGER.debug("3.1. Попытка выставления некорректного Email - ПРОВАЛ");
        String incorrectEmail = "incorrectEmailAddress";
        Map<String, Object> values = new HashMap<>();
        values.put("email", incorrectEmail);

        HttpEntity<Map<String, Object>> httpEntity2 = new HttpEntity<>(values);

        resultMap = restTemplate.exchange(baseUrl + "/users/{id}", HttpMethod.PUT, httpEntity2, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("errors"));
        assertTrue(resultMap.get("errors").toString().contains("email"));

        LOGGER.debug("3.2. Выставление Отчетства и Фамилии - УСПЕХ");
        String newMiddleName = "Тестович";
        String newLastName = "Тестов";

        values.clear();
        values.put("middleName", newMiddleName);
        values.put("lastName", newLastName);

        resultMap = restTemplate.exchange(baseUrl + "/users/{id}", HttpMethod.PUT, httpEntity2, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("3.3. Получаем изменённого пользователя из БД и проверяем Отчетство и Фамилию");
        createdUser = restTemplate.getForObject(baseUrl + "/users/{id}", User.class, createdUserId);
        assertTrue(createdUser != null);
        assertEquals(createdUser.getMiddleName(), newMiddleName);
        assertEquals(createdUser.getLastName(), newLastName);

        LOGGER.debug("4. Удаление пользователя");
        LOGGER.debug("4.1. Производим удаление");
        HttpEntity httpEntity3 = HttpEntity.EMPTY;
        resultMap = restTemplate.exchange(baseUrl + "/users/{id}", HttpMethod.DELETE, httpEntity3, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("4.2. Проверяем отсутствие юзера в БД");
        createdUser = restTemplate.getForObject(baseUrl + "/users/{id}", User.class, createdUserId);
        assertTrue(createdUser == null);
    }

    @Test
    public void loginTest() {

        String login = "rostislav";
        String password = "123";

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        //Create
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();

        Map result;
        result = restTemplate.postForObject(baseUrl + "/users/login", httpEntity, Map.class);

        System.out.println(result.toString());

        user.setLogin("www");
        user.setPassword("123");
        result = restTemplate.postForObject(baseUrl + "/users/login", httpEntity, Map.class);

        System.out.println(result.toString());
    }

}
