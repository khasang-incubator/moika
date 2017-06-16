package io.khasang.moika.integration;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pauls on 31.05.2017.
 */
public class WashAddrIntegrationTest {

    private HttpHeaders headers;

    private final String requestMapping = "http://localhost:8080/api/washAddr";
    private final String existingAddrStreet = "Пресня";
    private final String newAddrStreet = "Rosen schtrasse";
    private final String existingCity = "Москва";
    private final String newCityName = "Калининград";


    @Ignore
    @Before
    public void initTests() {
        System.out.println("Wash Address integration tests are beginning...");
        headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void testGetAddrList() {
        HttpEntity<List<WashAddr>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashAddr>> resultAll = restTemplate.exchange(
                requestMapping + "/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashAddr>>() {
                });
        List<WashAddr> resList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain WashAddr list", resList.isEmpty());

        WashAddr resAddr = resList.get(0);
        Assert.assertNotNull("Could not get any address from list", resAddr);

        Assert.assertNotNull("Address does not contain city", resAddr.getCity());
        City resCity = resAddr.getCity();
        Assert.assertNotEquals("Could not get city name", resCity.getName().isEmpty());
    }

    @Test
    public void testGetAddrById() {
        HttpEntity<WashAddr> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashAddr> resultAll = restTemplate.exchange(
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashAddr>() {
                }, (int)1);
        Assert.assertNotNull("Request body is incorrect", resultAll);

        WashAddr resAddr = resultAll.getBody();
        Assert.assertNotNull("Request body does not contain WashAddr", resAddr);

        Assert.assertTrue("Address does not exist " + existingAddrStreet, resAddr.getStreet().equalsIgnoreCase(existingAddrStreet));

        Assert.assertTrue("Address does  not contain city", resAddr.getCity().getName().equalsIgnoreCase(existingCity));
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateWashAddress() {
        final BigDecimal lat =  new BigDecimal("50.12345").setScale(5);
        final BigDecimal lon = new BigDecimal("40.54321").setScale(5);

        RestTemplate restTemplate;

        HttpEntity httpEntity;

        City newCity = new City(newCityName);
        WashAddr addr =  new WashAddr();
        addr.setCity(newCity);
        addr.setStreet(newAddrStreet);
        addr.setBuilding("6");
        addr.setCoordinate(new Coordinate(lat,lon ));

        httpEntity = new HttpEntity<>(addr, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        ResponseEntity<WashAddr> result = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping+"/add",
                HttpMethod.POST,
                httpEntity,
                WashAddr.class);
        Assert.assertNotNull("WashAddress Request body is incorrect", result);
        WashAddr resAddr = result.getBody();
        Assert.assertNotNull("WashAddress is null", resAddr);
        Assert.assertTrue("New addr street is not" + newAddrStreet, resAddr.getStreet().equalsIgnoreCase(newAddrStreet));
        Assert.assertEquals("New addr not equals coordinate lat ", lat, resAddr.getCoordinate().getLat());
        Assert.assertEquals("New addr not equals coordinate lon ", lon, resAddr.getCoordinate().getLon());
        Assert.assertTrue("New addr not contain city " + newCityName, resAddr.getCity().getName().equalsIgnoreCase(newCityName));
    }


    @Test
    @Transactional
    @Rollback
    public void testUpdateWashAddress() {
        final WashAddr baseAddr;
        HttpEntity<WashAddr> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashAddr> resultAll = restTemplate.exchange(
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashAddr>() {
                }, (int)1);
        Assert.assertNotNull("Request body is incorrect", resultAll);
        baseAddr = resultAll.getBody();
        Assert.assertNotNull("Request body does not contain WashAddr", baseAddr);
        Assert.assertTrue("Address does not exist " + existingAddrStreet, baseAddr.getStreet().equalsIgnoreCase(existingAddrStreet));
        Assert.assertTrue("Address does  not contain city", baseAddr.getCity().getName().equalsIgnoreCase(existingCity));

        baseAddr.setStreet(baseAddr.getStreet() + " updates");
        baseAddr.getCoordinate().setLat(baseAddr.getCoordinate().getLat().add(BigDecimal.ONE));

        httpEntity = new HttpEntity<>(baseAddr, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        ResponseEntity<WashAddr> result = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping+"/update",
                HttpMethod.PUT,
                httpEntity,
                WashAddr.class);
        Assert.assertNotNull("WashAddress Request body is incorrect", result);
        WashAddr resAddr = result.getBody();
        Assert.assertNotNull("WashAddress is null", resAddr);
        Assert.assertTrue("Updated addr city is not" + baseAddr.getCity().getName(),
                      resAddr.getCity().getName().equalsIgnoreCase(baseAddr.getCity().getName()));
        Assert.assertTrue("Updated addr street is not" + baseAddr.getStreet(),
                resAddr.getStreet().equalsIgnoreCase(baseAddr.getStreet()));
        Assert.assertEquals("Updated addr not equals coordinate lat ", baseAddr.getCoordinate().getLat(), resAddr.getCoordinate().getLat());
    }
}

