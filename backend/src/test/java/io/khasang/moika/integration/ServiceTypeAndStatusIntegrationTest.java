package io.khasang.moika.integration;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.entity.ServiceType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;


public class ServiceTypeAndStatusIntegrationTest {
    private HttpHeaders headers;

    private final String existingStatusCode = "ON";
    private final String testStatusCode = "TEST";
    private final String existingTypeCode = "WASH";
    private final String testTypeCode = "TEST";
    private final String requestMapping = "http://localhost:8080/api/service";

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Wash Address integration tests are beginning...");
        headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }


    @Test
    public void getServiceTypeList() {
        HttpEntity<List<ServiceType>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ServiceType>> result = restTemplate.exchange(
                requestMapping+"/type/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<ServiceType>>() {
                });
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());

        List<ServiceType> resServiceypeList = result.getBody();
        Assert.assertNotNull("Box type list is null", resServiceypeList);
        Assert.assertFalse("Box type list is empty", resServiceypeList.isEmpty());
    }

    @Test
    public void getServiceStatusList() {
        HttpEntity<List<ServiceStatus>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ServiceStatus>> result = restTemplate.exchange(
                requestMapping+"/status/list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<ServiceStatus>>() {
                });
        Assert.assertNotNull("Request body is incorrect", result);
        Assert.assertTrue("Request code not 202 " + result.getStatusCode().toString()
                , result.getStatusCode().is2xxSuccessful());
        List<ServiceStatus> resServiiceStatusList = result.getBody();
        Assert.assertNotNull("Box type list is null", resServiiceStatusList);
        Assert.assertFalse("Box type list is empty", resServiiceStatusList.isEmpty());
    }

    @Test
    public void getServiceStatus(){
        RestTemplate restTemplate = new RestTemplate();

        ServiceStatus serviceStatus = new ServiceStatus();

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для BoxStatus
        serviceStatus = restTemplate.exchange(
                requestMapping + "/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                ServiceStatus.class, existingStatusCode).getBody();
        Assert.assertNotNull("Could not get service status by code " + existingStatusCode, serviceStatus);


        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для BoxStatus
        serviceStatus = restTemplate.exchange(
                requestMapping + "/status/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                ServiceStatus.class, 1).getBody();
        Assert.assertNotNull("Could not get service status by ID 1" , serviceStatus);
    }

    @Test
    public void getServiceType(){
        RestTemplate restTemplate = new RestTemplate();

        ServiceType serviceType = new ServiceType();

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для BoxStatus
        serviceType = restTemplate.exchange(
                requestMapping + "/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                ServiceType.class, existingTypeCode).getBody();
        Assert.assertNotNull("Could not get service type by code " + existingTypeCode, serviceType);


        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для BoxStatus
        serviceType = restTemplate.exchange(
                requestMapping + "/type/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                ServiceType.class, 1).getBody();
        Assert.assertNotNull("Could not get service type by ID 1" , serviceType);
    }

    @Test
    @Transactional
    @Rollback
    public void createServiceStatus() {
        RestTemplate restTemplate = new RestTemplate();

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode(testStatusCode);
        serviceStatus.setStatusName("Test service status");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для ServiceType
        ServiceStatus resServiceStatus = restTemplate.exchange(
                requestMapping + "/status/add",
                HttpMethod.POST,
                httpEntity,
                ServiceStatus.class).getBody();
        Assert.assertNotNull("UPDATE service status  " + testStatusCode + " incorrect!", resServiceStatus);
        Assert.assertTrue("Could not get service type TEST", resServiceStatus.getStatusCode().equalsIgnoreCase(testStatusCode));
    }


    @Test
    @Transactional
    @Rollback
    public void createWashServiceType() {
        RestTemplate restTemplate = new RestTemplate();

        ServiceType serviceType = new ServiceType();
        serviceType.setTypeCode(testTypeCode);
        serviceType.setTypeName("Test service type");
        serviceType.setDescription("Some test type description");

        HttpEntity httpEntity;

        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для ServiceType
        ServiceType resServiceType = restTemplate.exchange(
                requestMapping + "/type/add",
                HttpMethod.POST,
                httpEntity,
                ServiceType.class).getBody();
        Assert.assertNotNull("UPDATE serviceType " + testTypeCode + " incorrect!", resServiceType);
        Assert.assertTrue("Could not get service type TEST", resServiceType.getTypeCode().equalsIgnoreCase(testTypeCode));
    }

    @Test
    @Transactional
    @Rollback
    public void updateWashServiceType() {
        RestTemplate restTemplate = new RestTemplate();

        ServiceType serviceType = new ServiceType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для ServiceType
        serviceType = restTemplate.exchange(
                requestMapping + "/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                ServiceType.class, testTypeCode).getBody();
        Assert.assertNotNull("Request of serviceType " + testTypeCode + " incorrect!", serviceType);
        Assert.assertTrue("Could not get service type " + testTypeCode, serviceType.getTypeCode().equalsIgnoreCase(testTypeCode));
        serviceType.setDescription("Some test type description");

        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для ServiceType
        serviceType = restTemplate.exchange(
                requestMapping + "/type/update",
                HttpMethod.PUT,
                httpEntity,
                ServiceType.class).getBody();
        Assert.assertNotNull("UPDATE serviceType " + testTypeCode + " incorrect!", serviceType);
        Assert.assertTrue("Could not get service type " + testTypeCode, serviceType.getDescription().equalsIgnoreCase("Some test type description"));
    }

    @Test
    @Transactional
    @Rollback
    public void updateWashServiceStatus() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity;
        ServiceStatus serviceStatus = new ServiceStatus();

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для ServiceType
        serviceStatus = restTemplate.exchange(
                requestMapping + "/status/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                ServiceStatus.class, testStatusCode).getBody();
        Assert.assertNotNull("Request of serviceType " + testStatusCode + " incorrect!", serviceStatus);
        Assert.assertTrue("Could not get service type " + testStatusCode, serviceStatus.getStatusCode().equalsIgnoreCase(testStatusCode));
        serviceStatus.setStatusName("Some test status description");

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для ServiceType
        serviceStatus = restTemplate.exchange(
                requestMapping + "/status/update",
                HttpMethod.PUT,
                httpEntity,
                ServiceStatus.class).getBody();
        Assert.assertNotNull("UPDATE serviceStatus " + existingStatusCode + " incorrect!", serviceStatus);
        Assert.assertTrue("Could not get service status " + existingStatusCode, serviceStatus.getStatusName().equalsIgnoreCase("Some test Status description"));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteWashServiceType() {
        RestTemplate restTemplate = new RestTemplate();

        ServiceType serviceType = new ServiceType();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для ServiceType
        try {
            ResponseEntity<ServiceType> result = restTemplate.exchange(
                    requestMapping + "/type/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    ServiceType.class, testTypeCode);
            Assert.assertTrue("Response code is not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
            serviceType = result.getBody();
            Assert.assertNotNull("Response of serviceType " + testTypeCode + " is incorrect!", serviceType);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Response code is 4xx " + e.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }

        httpEntity = new HttpEntity<>(serviceType.getId(), headers); //подготовили запрос для ServiceType
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping + "/type/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, serviceType.getId());
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString(), isDeleted.getStatusCode().is2xxSuccessful());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteWashServiceStatus() {
        RestTemplate restTemplate = new RestTemplate();

        ServiceStatus serviceStatus = new ServiceStatus();

        HttpEntity httpEntity;
        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для ServiceStatus
        try {
            ResponseEntity<ServiceStatus> result = restTemplate.exchange(
                    requestMapping + "/status/byCode/{code}",
                    HttpMethod.GET,
                    httpEntity,
                    ServiceStatus.class, testStatusCode);
            Assert.assertTrue("Response code is not 202 " + result.getStatusCode().toString(), result.getStatusCode().is2xxSuccessful());
            serviceStatus = result.getBody();
            Assert.assertNotNull("Response of serviceStatus " + testStatusCode + " is incorrect!", serviceStatus);
        } catch (HttpClientErrorException e) {
            Assert.assertTrue("Response code is 4xx " + e.getStatusCode().toString(), e.getStatusCode().is4xxClientError());
        }

        httpEntity = new HttpEntity<>(serviceStatus.getId(), headers); //подготовили запрос для ServiceStatus
        ResponseEntity<Boolean> isDeleted = restTemplate.exchange(
                requestMapping + "/status/delete/{id}",
                HttpMethod.DELETE,
                httpEntity,
                Boolean.class, serviceStatus.getId());
        Assert.assertTrue("Request code is not 20x " + isDeleted.getStatusCode().toString(), isDeleted.getStatusCode().is2xxSuccessful());
    }
}
