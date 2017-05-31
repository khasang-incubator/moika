package io.khasang.moika.integration;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
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

public class WashBoxIntegrationTest {


    @Ignore
    @Before
    public void initTests() {
        System.out.println("WashBox Tests are beginning...");
    }

    private final int idFclt = 8;
    private final int idBox = 9;
    private final String boxName = "Бокс № TEST";
    private final String existingFasility = "Мойка на Помойке";
    private final String existingStatusCode = "WORKING";
    private final String testStatusCode = "TEST";
    private final String existingTypeCode = "MEDIUM";
    private final String testTypeCode = "TEST";
    private final String requestMapping = "http://localhost:8080/api/washBox";
    private int addedTestBox;


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
                requestMapping + "/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxStatus.class, existingStatusCode).getBody();
        Assert.assertNotNull("Could not get box status " + existingStatusCode, boxStatus);

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                requestMapping + "/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxType.class, existingTypeCode).getBody();
        Assert.assertNotNull("Could not get box type " + existingTypeCode, boxType);

        WashBox box = new WashBox();  // подготовили класс для тестирования
        box.setBoxName(boxName);
        box.setIdFacility(idFclt);
        box.setDescription(box.getBoxName() + " 'это тестовый бокс, можно удалить");
        box.setBoxStatusEntity(boxStatus);
        box.setBoxTypeEntity(boxType);

        httpEntity = new HttpEntity<>(box, headers); //подготовили запрос га добавление Facility
        restTemplate = new RestTemplate();
        ResponseEntity<WashBox> result = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                requestMapping + "/add",
                HttpMethod.POST,
                httpEntity,
                WashBox.class);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
        WashBox resBox = result.getBody();

        Assert.assertTrue("New box status not " + existingStatusCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(existingStatusCode));
        Assert.assertTrue("New box type not " + existingTypeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(existingTypeCode));
        addedTestBox = resBox.getId();
    }

    @Test
    @Transactional
    @Rollback
    public void updateWashBox() {
        final String newDesc = "Some test description" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());

        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<WashBox> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WashBox> result = restTemplate.exchange(
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashBox>() {
                }, idBox);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 20X, but " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());

        WashBox box = result.getBody();
        Assert.assertNotNull("Request of WashBax " + idBox + " incorrect!", box);
        Assert.assertEquals("Incorrest Wash box Id ", idBox, box.getId());

        box.setDescription(newDesc);

        httpEntity = new HttpEntity<>(box, headers); //подготовили запрос для BoxType
        WashBox resBox = restTemplate.exchange(
                requestMapping + "/update",
                HttpMethod.PUT,
                httpEntity,
                WashBox.class).getBody();
        Assert.assertNotNull("UPDATE boxType " + idBox + " is null!", resBox);
        Assert.assertTrue("Could not update box descr! Expected " + newDesc + ", but " + resBox.getDescription(), resBox.getDescription().equalsIgnoreCase(newDesc));
    }

    @Test
    public void getBoxListOnFclt() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashBox>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashBox>> result = restTemplate.exchange(
                requestMapping + "/inFacility/{idFacility}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashBox>>() {
                }, idFclt);
        Assert.assertNotNull("Request body is incorrect", result);
        List<WashBox> resBoxList = result.getBody();

        Assert.assertNotNull("Request body does not contain WashBoxLists", resBoxList);
        Assert.assertTrue("Facility does  not contain boxes", resBoxList.size() > 0);
        for (WashBox box : resBoxList) {
            if (box.getId() == addedTestBox) {
                Assert.assertTrue("Facility  box status not " + existingStatusCode + ", but " + box.getBoxStatusEntity().getStatusCode()
                        , box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(existingStatusCode));
                Assert.assertTrue("Facility  box type not " + existingTypeCode + ", but " + box.getBoxTypeEntity().getTypeCode()
                        , box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(existingTypeCode));
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
                requestMapping + "/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<WashBox>() {
                }, idBox);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
        WashBox resBox = result.getBody();
        Assert.assertNotNull("Request body does not contain wash box", resBox);
        Assert.assertEquals("Facility does  not contain boxes", resBox.getId(), idBox);

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping + "/byId/{id}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<WashBox>() {
                    }, 0);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }

    @Test
    public void getBoxTypeByCode() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<WashBox> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<BoxType> result = restTemplate.exchange(
                requestMapping + "/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<BoxType>() {
                }, existingTypeCode);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
        BoxType resType = result.getBody();
        Assert.assertNotNull("Request body does not contain wash box type", resType);
        Assert.assertTrue("Box type code does not exists", resType.getTypeCode().equalsIgnoreCase(existingTypeCode));

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping + "/type/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<BoxType>() {
                    }, "XZE");
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }


    @Test
    public void getBoxStatusByCode() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<WashBox> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<BoxStatus> result = restTemplate.exchange(
                requestMapping + "/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<BoxStatus>() {
                }, existingStatusCode);
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
        BoxStatus resStatus = result.getBody();
        Assert.assertNotNull("Request body does not contain wash box type", resStatus);
        Assert.assertTrue("Box status code does not exists", resStatus.getStatusCode().equalsIgnoreCase(existingStatusCode));

        //Запрос о несуществующей сущности. Ожидам exception и StatusCode 404
        try {
            result = restTemplate.exchange(
                    requestMapping + "/status/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<BoxStatus>() {
                    }, "XYZ");
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Request code 4xx " + result.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }
    }


    @Test
    public void getBoxTypeList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<BoxType>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<BoxType>> result = restTemplate.exchange(
                requestMapping+"/type/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<BoxType>>() {
                });
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());
        List<BoxType> resBoxTypeList = result.getBody();
        Assert.assertNotNull("Box type list is null", resBoxTypeList);
        Assert.assertFalse("Box type list is empty", resBoxTypeList.isEmpty());
    }

    @Test
    public void getBoxStatusList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<BoxStatus>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<BoxStatus>> result = restTemplate.exchange(
                requestMapping+"/status/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<BoxStatus>>() {
                });
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());
        List<BoxStatus> resBoxStatusList = result.getBody();
        Assert.assertNotNull("Box type list is null", resBoxStatusList);
        Assert.assertFalse("Box type list is empty", resBoxStatusList.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void createWashBoxType() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxType boxType = new BoxType();
        boxType.setTypeCode(testTypeCode);
        boxType.setTypeName("Test box type");
        boxType.setDescription("Some test type description");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        BoxType resBoxType = restTemplate.exchange(
                requestMapping + "/type/add",
                HttpMethod.POST,
                httpEntity,
                BoxType.class).getBody();
        Assert.assertNotNull("UPDATE boxType " + testTypeCode + " incorrect!", resBoxType);
        Assert.assertTrue("Could not get box type TEST", resBoxType.getTypeCode().equalsIgnoreCase(testTypeCode));
    }

    @Test
    @Transactional
    @Rollback
    public void createWashBoxStatus() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxStatus boxStatus = new BoxStatus();
        boxStatus.setStatusCode(testStatusCode);
        boxStatus.setStatusName("Test box status");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxType
        BoxStatus resBoxStatus = restTemplate.exchange(
                requestMapping + "/status/add",
                HttpMethod.POST,
                httpEntity,
                BoxStatus.class).getBody();
        Assert.assertNotNull("UPDATE boxStaus " + testStatusCode + " incorrect!", resBoxStatus);
        Assert.assertTrue("Could not get box type TEST", resBoxStatus.getStatusCode().equalsIgnoreCase(testStatusCode));
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
                requestMapping + "/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxType.class, testTypeCode).getBody();
        Assert.assertNotNull("Request of boxType " + testTypeCode + " incorrect!", boxType);
        Assert.assertTrue("Could not get box type " + testTypeCode, boxType.getTypeCode().equalsIgnoreCase(testTypeCode));
        boxType.setDescription("Some test type description");

        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        boxType = restTemplate.exchange(
                requestMapping + "/type/update",
                HttpMethod.PUT,
                httpEntity,
                BoxType.class).getBody();
        Assert.assertNotNull("UPDATE boxType " + testTypeCode + " incorrect!", boxType);
        Assert.assertTrue("Could not get box type " + testTypeCode, boxType.getDescription().equalsIgnoreCase("Some test type description"));
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
                requestMapping + "/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                BoxStatus.class, testStatusCode).getBody();
        Assert.assertNotNull("Request of boxType " + testStatusCode + " incorrect!", boxStatus);
        Assert.assertTrue("Could not get box type " + testStatusCode, boxStatus.getStatusCode().equalsIgnoreCase(testStatusCode));
        boxStatus.setStatusName("Some test status description");

        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxType
        boxStatus = restTemplate.exchange(
                requestMapping + "/status/update",
                HttpMethod.PUT,
                httpEntity,
                BoxStatus.class).getBody();
        Assert.assertNotNull("UPDATE boxStatus " + existingStatusCode + " incorrect!", boxStatus);
        Assert.assertTrue("Could not get box status " + existingStatusCode, boxStatus.getStatusName().equalsIgnoreCase("Some test Status description"));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteWashBoxType() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxType boxType = new BoxType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(boxType, headers); //подготовили запрос для BoxType
        try {
            ResponseEntity<BoxType> result = restTemplate.exchange(
                    requestMapping + "/type/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    BoxType.class, testTypeCode);
            Assert.assertTrue("Response code is not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
            boxType = result.getBody();
            Assert.assertNotNull("Response of boxType " + testTypeCode + " is incorrect!", boxType);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Response code is 4xx " + e.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }

        httpEntity = new HttpEntity<>(boxType.getId(), headers); //подготовили запрос для BoxType
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping + "/type/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, boxType.getId());
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString(), isDeleted.getStatusCode().is2xxSuccessful());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteWashBoxStatus() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        BoxStatus boxStatus = new BoxStatus();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(boxStatus, headers); //подготовили запрос для BoxStatus
        try {
            ResponseEntity<BoxStatus> result = restTemplate.exchange(
                    requestMapping + "/status/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    BoxStatus.class, testStatusCode);
            Assert.assertTrue("Response code is not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
            boxStatus = result.getBody();
            Assert.assertNotNull("Response of boxStatus " + testStatusCode + " is incorrect!", boxStatus);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Response code is 4xx " + e.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }

        httpEntity = new HttpEntity<>(boxStatus.getId(), headers); //подготовили запрос для BoxStatus
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping + "/status/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, boxStatus.getId());
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString(), isDeleted.getStatusCode().is2xxSuccessful());
    }
}


