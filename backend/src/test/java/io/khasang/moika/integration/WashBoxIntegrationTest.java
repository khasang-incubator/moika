package io.khasang.moika.integration;

import io.khasang.moika.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class WashBoxIntegrationTest {


    @Ignore
    @Before
    public void initTests() {
        System.out.println("WashBox Tests are beginning...");
    }

    final int idFclt = 8;
    final int idBox = 9;
    final String boxName = "Бокс № TEST";
    final String existingFasity = "Мойка на Помойке";
    final String statusCode = "WORKING";
    final String typeCode = "MEDIUM";

  //  @Ignore
    @Test
    @Transactional
    @Rollback
    public void createWashBox() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxStatus boxStatus = new BoxStatus();
        BoxType boxType = new BoxType();

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxStatus
        boxStatus = restTemplate.exchange(
                "http://localhost:8080/api/boxStatus/{code}/",
                HttpMethod.GET,
                httpEntity,
                BoxStatus.class, statusCode).getBody();
        Assert.assertNotNull("Could not get box status " + statusCode, boxStatus);

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                "http://localhost:8080/api/boxType/{code}/",
                HttpMethod.GET,
                httpEntity,
                BoxType.class, typeCode).getBody();
        Assert.assertNotNull("Could not get box type " + typeCode, boxType);

        WashBox box = new WashBox();  // подготовили класс для тестирования
        box.setBoxName(boxName);
        box.setIdFacility(idFclt);
        box.setDescription(box.getBoxName() + " 'это тестовый бокс, можно удалить");
        box.setBoxStatusEntity(boxStatus);
        box.setBoxTypeEntity(boxType);

        httpEntity = new HttpEntity<>(box, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        WashBox resBox = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                "http://localhost:8080/api/washBox/add",
                HttpMethod.POST,
                httpEntity,
                WashBox.class).getBody();

        if (resBox.getBoxName().equalsIgnoreCase(boxName)) {
            Assert.assertTrue("Facility  box status not " + statusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(statusCode));
            Assert.assertTrue("Facility  box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
        }
    }


    @Test
    public void getBoxListOnFclt() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashBox>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashBox>> result = restTemplate.exchange(
                "http://localhost:8080/api/washBox/inFacility/{idFacility}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashBox>>() {
                }, idFclt);
        Assert.assertNotNull("Reques body is incorrect", result);
        List<WashBox> resBoxList = result.getBody();

        Assert.assertNotNull("Request body does not contain WashBoxLists", resBoxList);
        Assert.assertTrue("Facility does  not contain boxes", resBoxList.size() > 0);
        for (WashBox box : resBoxList) {
            if (box.getBoxName().equalsIgnoreCase("Бокс № 1")) {
                Assert.assertTrue("Facility  box status not " + statusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(statusCode));
                Assert.assertTrue("Facility  box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
                break;
            }
        }
     }

    @Test
    public void getBoxById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<WashBox> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashBox> result = restTemplate.exchange(
                "http://localhost:8080/api/washBox/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashBox>() {
                }, idBox );
        Assert.assertNotNull("Reques body is incorrect", result);
        Assert.assertEquals("Reques code non 202", result.getStatusCode().is2xxSuccessful());

        result = restTemplate.exchange(
                "http://localhost:8080/api/washBox/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashBox>() {
                }, 0 );
        Assert.assertEquals("Reques body is incorrect", result.getStatusCode().is4xxClientError());
    }

    @Test
    @Transactional
    @Rollback
    public void createWashBoxType() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxType boxType = new BoxType();
        boxType.setTypeCode("TEST");
        boxType.setTypeName("Test box type");
        boxType.setDescription("Some test type description");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        BoxType resBoxType = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxType/add",
                HttpMethod.POST,
                httpEntity,
                BoxType.class, typeCode).getBody();
        Assert.assertNotNull("UPDATE boxType "+typeCode+" incorrect!", resBoxType);
        Assert.assertTrue("Could not get box type TEST", resBoxType.getTypeCode().equalsIgnoreCase("TEST"));
    }

    @Test
    @Transactional
    @Rollback
    public void createWashBoxStatus() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxStatus boxStatus = new BoxStatus();
        boxStatus.setStatusCode("TEST");
        boxStatus.setStatusName("Test box status");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxType
        BoxStatus resBoxStatus = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxStatus/add",
                HttpMethod.POST,
                httpEntity,
                BoxStatus.class, typeCode).getBody();
        Assert.assertNotNull("UPDATE boxStaus "+statusCode+" incorrect!", resBoxStatus);
        Assert.assertTrue("Could not get box type TEST" , resBoxStatus.getStatusCode().equalsIgnoreCase("TEST"));
    }


    @Test
    @Transactional
    @Rollback
    public void updateWashBoxType() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxType boxType = new BoxType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxType/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxType.class, typeCode).getBody();
        Assert.assertNotNull("Request of boxType "+typeCode+" incorrect!");
        Assert.assertTrue("Could not get box type " + typeCode, boxType.getTypeCode().equalsIgnoreCase(typeCode));
        boxType.setDescription("Some test type description");

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxType/update",
                HttpMethod.PUT,
                httpEntity,
                BoxType.class).getBody();
        Assert.assertNotNull("UPDATE boxType "+typeCode+" incorrect!", boxType);
        Assert.assertTrue("Could not get box type " + typeCode, boxType.getDescription().equalsIgnoreCase("Some test type description"));
    }

    @Test
    @Transactional
    @Rollback
    public void updateWashBoxStatus() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity;
        BoxStatus boxStatus = new BoxStatus();

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxType
        boxStatus = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxStatus/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxStatus.class, statusCode).getBody();
        Assert.assertNotNull("Request of boxType "+statusCode+" incorrect!");
        Assert.assertTrue("Could not get box type " + statusCode, boxStatus.getStatusCode().equalsIgnoreCase(statusCode));
        boxStatus.setStatusName("Some test ststus description");

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxType
        boxStatus = restTemplate.exchange(
                "http://localhost:8080/api/washBox/boxStatus/update",
                HttpMethod.PUT,
                httpEntity,
                BoxStatus.class).getBody();
        Assert.assertNotNull("UPDATE boxStatus "+statusCode+" incorrect!");
        Assert.assertTrue("Could not get box status " + statusCode, boxStatus.getStatusName().equalsIgnoreCase("Some test Status description"));
    }
}


