package io.khasang.moika.integration;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.WashAddr;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by pauls on 31.05.2017.
 */
public class WashAddrIntegrationTest {


    @Ignore
    @Before
    public void initTests() {
        System.out.println("Wash Address integration tests are beginning...");
    }

    private final String requestMapping = "http://localhost:8080/api/washAddr";
    private final String existingAddrStreet = "Пресня";
    private final String newAddrStreet = "Princesse Luisa Allee";
    private final String existingCity = "Москва";
    private final String newCityName = "Калининград";


    @Test
    public void getAddrList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashAddr>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashAddr>> resultAll = restTemplate.exchange(
                requestMapping + "/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashAddr>>() {
                });
        List<WashAddr> resList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain WashAddr", resList.isEmpty());

        WashAddr resAddr = resList.get(0);
        Assert.assertNotNull("Could not get any address from list", resAddr);

        Assert.assertNotNull("Address does not contain city", resAddr.getCity());
        City resCity = resAddr.getCity();
        Assert.assertNotEquals("Could not get city name", resCity.getName().isEmpty());
    }

    @Test
    public void getAddrById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        HttpEntity<List<WashAddr>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashAddr> resultAll = restTemplate.exchange(
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashAddr>() {
                }, (int)1);
        Assert.assertNotNull("Reques body is incorrect", resultAll);

        WashAddr resAddr = resultAll.getBody();
        Assert.assertNotNull("Request body does not contain WashFacilities", resAddr);

        Assert.assertTrue("Address does not exist " + existingAddrStreet, resAddr.getStreet().equalsIgnoreCase(existingAddrStreet));

        Assert.assertTrue("Address does  not contain city", resAddr.getCity().getName().equalsIgnoreCase(existingCity));
    }

    @Test
    @Transactional
    @Rollback
    public void createWashAddress() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity httpEntity;
        City newCity = new City(newCityName);
        httpEntity = new HttpEntity<>(newCity, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        City resCity = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping+"/city/add",
                HttpMethod.POST,
                httpEntity,
                City.class).getBody();
        Assert.assertNotNull("Could not add city", resCity);

        WashAddr addr =  new WashAddr();
        addr.setCity(newCity);
        addr.setStreet(newAddrStreet);
        addr.setBuilding("233");

        httpEntity = new HttpEntity<>(addr, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        ResponseEntity<WashAddr> result = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping+"/add",
                HttpMethod.POST,
                httpEntity,
                WashAddr.class);
        Assert.assertNotNull("WashAddress Request body is incorrect", result);
        WashAddr resAddr = result.getBody();

        Assert.assertTrue("New addr city is not " + newCityName, resAddr.getCity().getName().equalsIgnoreCase(newCityName));
        Assert.assertTrue("New addr city is not" + newAddrStreet, resAddr.getStreet().equalsIgnoreCase(newAddrStreet));
    }

}

