package io.khasang.moika.integration;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.CarType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CarIntegrationTest {

    HttpHeaders headers;
    private final String carTestNote = "Красный таз";
    private final String carTestDescr = "Чиста для поцанов " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
    private final String carTestNum = "M777CT99";
    private final String existingCarNum = "Р982ЕУ39RUS";
    private final String testCarModel = "FW";
    private final String existingCarTypeCode = "SUV";
    private final String testCarTypeCode = "TEST";
    long testId = 1;
    private final String requestMapping = "http://localhost:8080/api/car";

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Car Tests are beginning...");
        headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    @Transactional
    @Rollback
    public void addCarTest() {
        RestTemplate restTemplate = new RestTemplate();

        CarType carType = new CarType();

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(carType, headers); //подготовили запрос для BoxType
        carType = restTemplate.exchange(
                requestMapping+"/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                CarType.class, existingCarTypeCode).getBody();
        Assert.assertNotNull("Could not get car type " + existingCarTypeCode, carType);
        Assert.assertTrue("Setting Car type code not " + existingCarTypeCode + ", but " + carType.getTypeCode(), carType.getTypeCode().equalsIgnoreCase(existingCarTypeCode));

        Car car = new Car();
        car.setCarNumber(carTestNum);
        car.setCarTypeEntity(carType);
        car.setNote(carTestNote);
        car.setCarModel(testCarModel);
        car.setStatus(Short.parseShort("1"));

        httpEntity = new HttpEntity<>(car, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        Car retCar = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping+"/add",
                HttpMethod.POST,
                httpEntity,
                Car.class).getBody();

        Assert.assertNotNull("Return car is null", retCar);
        Assert.assertNotNull("Return car typeEntity is null", retCar.getCarTypeEntity());
        Assert.assertTrue("Car type code not " + existingCarTypeCode + ", but " + retCar.getCarTypeEntity().getTypeCode()
                , retCar.getCarTypeEntity().getTypeCode().equalsIgnoreCase(existingCarTypeCode));
        Assert.assertTrue("Car number  not " + carTestNum + ", but " + retCar.getCarNumber()
                , retCar.getCarNumber().equalsIgnoreCase(carTestNum));
        Assert.assertTrue("Car note not " + carTestNote + ", but " + retCar.getNote()
                , retCar.getNote().equalsIgnoreCase(carTestNote));
    }

    @Test
    @Transactional
    @Rollback
    public void udateCarTest() {

        HttpEntity<Car> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Car> result = restTemplate.exchange( //подготовили запрос для Car
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                Car.class, testId);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 20X, but " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());

        Car car = result.getBody();
        Assert.assertNotNull("Request of car is incorrect!", car);
        Assert.assertEquals("Could not get car " + car.getNote(), car.getId(), testId);

        car.setNote(carTestNote);

        httpEntity = new HttpEntity<>(car, headers); //подготовили запрос для Car
        Car retCar = restTemplate.exchange(
                requestMapping+"/update",
                HttpMethod.PUT,
                httpEntity,
                Car.class).getBody();
        Assert.assertNotNull("Return car is null", retCar);
        Assert.assertTrue("Car note  not " + carTestNote + ", but " + retCar.getNote()
                , retCar.getNote().equalsIgnoreCase(carTestNote));
    }

    @Ignore
    @Test
    public void deleteCar() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(testId, headers); //подготовили запрос для BoxType
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping+"/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, testId);
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString()
                , isDeleted.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void getCarById() {
        HttpEntity<Car> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Car> result = restTemplate.exchange(
                requestMapping+"/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Car>() {
                }, testId);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());

        Car resCar = result.getBody();
        Assert.assertEquals("Ret car ID is not " +testId + ", but "+ resCar.getId(), testId, resCar.getId());

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    "http://localhost:8080/api/washBox/{id}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Car>() {
                    }, 0);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }


    @Test
    public void getCarList() {
        HttpEntity<List<Car>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Car>> result = restTemplate.exchange(
                requestMapping+"/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Car>>() {
                });
        Assert.assertNotNull("Reques body is incorrect", result);
        List<Car> resCarList = result.getBody();

        Assert.assertNotNull("Request body does not contain CarLists", resCarList);
        Assert.assertTrue("CarList does  not contain cars", resCarList.size() > 0);
        boolean isCarExists = false;
        for (Car car : resCarList) {
            if (car.getCarNumber().equalsIgnoreCase(existingCarNum)) {
                isCarExists = true;
                break;
            }
        }
        Assert.assertTrue("No existing car " + existingCarNum, isCarExists);
    }


    @Test
    public void getCarByNum() {
        HttpEntity<Car> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Car> result = restTemplate.exchange(
                requestMapping+"/byNumber/{number}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Car>() {
                }, existingCarNum);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
        Car resCar = result.getBody();
        Assert.assertNotNull("Ret car is null", resCar);
        Assert.assertTrue("Ret car number not " + existingCarNum + ", but " + resCar.getCarNumber(), resCar.getCarNumber().equalsIgnoreCase(existingCarNum));

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    "http://localhost:8080/api/byNumber/{number}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Car>() {
                    }, existingCarNum);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }

    @Test
    public void getCarListByType() {
        HttpEntity<List<Car>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Car>> result = restTemplate.exchange(
                requestMapping+"/byType/{code}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Car>>() {
                }, existingCarTypeCode);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());
        List<Car> resCarList = result.getBody();
        Assert.assertNotNull("Car list is null", resCarList);
        Assert.assertFalse("Car list is empty", resCarList.isEmpty());

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping+"/byType/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<Car>>() {
                    }, "ZYX");
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString()
                    , e.getStatusCode().is4xxClientError());
        }
    }


    @Test
    public void getCarTypeList() {
        HttpEntity<List<CarType>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CarType>> result = restTemplate.exchange(
                requestMapping+"/type/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<CarType>>() {
                });
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());
        List<CarType> resCarTypeList = result.getBody();
        Assert.assertNotNull("Car type list is null", resCarTypeList);
        Assert.assertFalse("Car type list is empty", resCarTypeList.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void createCarType() {
        RestTemplate restTemplate = new RestTemplate();

        CarType carType = new CarType();
        carType.setTypeCode(testCarTypeCode);
        carType.setTypeName("Test car type");
        carType.setDescription("Some test type description");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(carType, headers); //подготовили запрос для CarType
        CarType resCarType = restTemplate.exchange(
                requestMapping+"/type/add",
                HttpMethod.POST,
                httpEntity,
                CarType.class).getBody();
        Assert.assertNotNull("UPDATE carType " + testCarTypeCode + " incorrect!", resCarType);
        Assert.assertTrue("Could not get car type TEST", resCarType.getTypeCode().equalsIgnoreCase(testCarTypeCode));
    }

    @Test
    @Transactional
    @Rollback
    public void updateCarType() {
        RestTemplate restTemplate = new RestTemplate();

        CarType carType = new CarType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(carType, headers); //подготовили запрос для CarType
        carType = restTemplate.exchange(
                requestMapping+"/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                CarType.class, existingCarTypeCode).getBody();
        Assert.assertNotNull("Request of carType " + existingCarTypeCode + " incorrect!", carType);
        Assert.assertTrue("Could not get car type " + existingCarTypeCode, carType.getTypeCode().equalsIgnoreCase(existingCarTypeCode));
        carType.setDescription("Some test type description");

        httpEntity = new HttpEntity<>(carType, headers); //подготовили запрос для CarType
        carType = restTemplate.exchange(
                requestMapping+"/type/update",
                HttpMethod.PUT,
                httpEntity,
                CarType.class).getBody();
        Assert.assertNotNull("UPDATE carType " + existingCarTypeCode + " incorrect!", carType);
        Assert.assertTrue("Could not get car type " + existingCarTypeCode, carType.getDescription().equalsIgnoreCase("Some test type description"));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteCarType() {
        RestTemplate restTemplate = new RestTemplate();

        CarType carType = new CarType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(carType, headers); //подготовили запрос для CarType
        try {
            ResponseEntity<CarType> result = restTemplate.exchange(
                    requestMapping+"/type/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    CarType.class, testCarTypeCode);
            Assert.assertTrue("Response code is not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
            carType = result.getBody();
            Assert.assertNotNull("Response of carType " + testCarTypeCode + " is incorrect!", carType);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Response code is 4xx " + e.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }

        httpEntity = new HttpEntity<>(carType.getId(), headers); //подготовили запрос для CarType
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping+"/type/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, carType.getId());
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString(), isDeleted.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getCarTypeById() {
        HttpEntity<CarType> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CarType> result = restTemplate.exchange(
                requestMapping+"/type/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<CarType>() {
                }, testId);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());

        CarType resCarType = result.getBody();
        Assert.assertEquals("Return car type ID is not 1, but "+ resCarType.getId(), testId, resCarType.getId());

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping+"/type/byId/{id}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CarType>() {
                    }, 0);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }


    @Test
    public void getCarTypeByCode() {
        HttpEntity<CarType> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CarType> result = restTemplate.exchange(
                requestMapping+"/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<CarType>() {
                }, existingCarTypeCode);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());

        CarType resCarType = result.getBody();
        Assert.assertEquals("Return car type ID is incorrect", existingCarTypeCode, resCarType.getTypeCode());

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping+"/type/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CarType>() {
                    }, "XXXX");
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }

}

