package io.khasang.moika.integration;

import io.khasang.moika.dao.*;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WashServiceIntegrationTest {


    @Autowired
    private MoikaServiceDataAccessService moikaServiceDataAccess;
    @Autowired
    private ServiceStatusDao serviceStatusDao;
    @Autowired
    private ServiceTypeDao serviceTypeDao;
    @Autowired
    private CarTypeDao carTypeDao;

    private final String serviceName = "Мойка силой мысли";
    private final String testDescr = "к нам на полставки устроился Йода";

    private final String serviceTypeCode = "WASH";
    private final String serviceStatusCode = "PLAN";
    private final String carTypeCode1 = "CAR";
    private final String carTypeCode2 = "SUV";

    private final class TestResult{
        String serviceCode;
        BigDecimal cost = null;
        int duration = 0;
    }

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Tests adding wash service are beginning...");
    }


    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createTestWashService() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity;

        MoikaService moikaService = new MoikaService(); // подготовили объект для тестирования
        moikaService.setName(serviceName);
        moikaService.setIdFacility(3);
        moikaService.setDescription(testDescr);


        ServiceStatus serviceStatus  = new ServiceStatus();

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для BoxStatus
        serviceStatus = restTemplate.exchange(
                "http://localhost:8080/service/status/byId/{id}",
                HttpMethod.GET,
                httpEntity,
                ServiceStatus.class,serviceStatusCode).getBody();
        Assert.assertNotNull("Could not get service status "+serviceStatusCode+"!",serviceStatus);
        moikaService.setIdStatus((short)serviceStatus.getId());
        moikaService.setServiceStatusEntity(serviceStatus);

        ServiceType serviceType = new  ServiceType();
        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для BoxType
        serviceType = restTemplate.exchange(
                "http://localhost:8080/service/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                ServiceType.class,serviceTypeCode).getBody();
        Assert.assertNotNull("Could not get service type "+serviceTypeCode,serviceType);
        moikaService.setServiceTypeEntity(serviceType);


        List<MoikaService> serviceList = new ArrayList<>();
        WashService washService =  (WashService) moikaService;

        CarType carType = restTemplate.exchange(
                "http://localhost:8080/car/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                CarType.class,carTypeCode1).getBody();
        Assert.assertNotNull("Could not get car type "+carTypeCode1,serviceType);

        washService.setCarTypeEntity(carType);
        washService.setServiceCost(new BigDecimal("3500.00"));
        washService.setServiceDuration(10);
        serviceList.add(washService);

        washService = new WashService();
        carType = restTemplate.exchange(
                "http://localhost:8080/car/type/byCode/{code}",
                HttpMethod.GET,
                httpEntity,
                CarType.class,carTypeCode2).getBody();
        Assert.assertNotNull("Could not get car type "+carTypeCode2,serviceType);
        washService.setServiceStatusEntity(serviceStatus);
        washService.setCarTypeEntity(carType);
        washService.setServiceCost(new BigDecimal("5500.00"));
        washService.setServiceDuration(20);
        serviceList.add(washService);

        MoikaService testService = new MoikaService(); // подготовили объект для возврата
        try {
            testService = moikaServiceDataAccess.addService(washService);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Wash service is null", testService);


        Assert.assertTrue("Service types  not  \"Мойка силой мысли\"", testService.getName().equalsIgnoreCase(serviceName));
        Assert.assertTrue("Service types  not  \"WASH\"", testService.getServiceTypeCode().equalsIgnoreCase("WASH"));
        Assert.assertEquals("Service name \"Мойка силой мысли\" not cost", new BigDecimal("5500.00").setScale(2),testService.getServiceCost().setScale(2));
        Assert.assertEquals("Service name \"Мойка силой мысли\" not last", 20L, testService.getServiceDuration().longValue());
    }

    /*
    private TestResult fillTestResult(List<MoikaService> serviceList, int serviceId, String carTypeCode){
        TestResult testResult = new TestResult();
        for (MoikaService item : serviceList) {
            if (item.getId() == serviceId) {
                testResult.isWashCode = true;
                List<MoikaService> addInfo = item.getServiceAddInfo();
                testResult = fillTestServiceAddInfo(testResult, addInfo, carTypeCode);
            }
        }
        return  testResult;
    }

    private TestResult fillTestServiceAddInfo(TestResult testResult, List<MoikaService> addInfo, String carTypeCode){
        for (MoikaService serviceInfo : addInfo) {
            if (((WashService) serviceInfo).getCarTypeEntity().getTypeCode().equals(carTypeCode)) {
                testResult.cost = serviceInfo.getServiceCost();
                testResult.duration = serviceInfo.getServiceDuration();
                break;
            }
        }
        return testResult;
    }
*/
    private TestResult fillTestResult(List<? extends MoikaService> serviceList, long serviceId){
        TestResult testResult = new TestResult();
        for (MoikaService item : serviceList) {
            if (item.getId() == serviceId) {
                testResult.serviceCode = item.getServiceTypeCode();
                testResult.cost = item.getServiceCost();
                testResult.duration = item.getServiceDuration();
            }
        }
        return  testResult;
    }


    @Test
    public void getWashServiceListByService() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashService>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashService>> resultAll = restTemplate.exchange(
                "http://localhost:8080/service/washServiceListbyService",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashService>>() {
                });
        List<WashService> serviceList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain Wash Services", serviceList.isEmpty());

        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());

        TestResult testResult = fillTestResult( serviceList, 11);

        Assert.assertTrue("Service  list not contain name \"WASH\"", testResult.serviceCode.equalsIgnoreCase("WASH"));
        Assert.assertEquals("Service  list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), testResult.cost);
        Assert.assertEquals("Service  list  name \"Ручная мойка машины\" not last", 300, testResult.duration);
    }

    @Test
    public void getWashServiceList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashService>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashService>> resultAll = restTemplate.exchange(
                "http://localhost:8080/MoikaService/washServiceList",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashService>>() {
                });
        List<WashService> serviceList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain Wash Services", serviceList.isEmpty());

        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());

       // TestResult testResult = fillTestResult( serviceList, 11, carTypeCode1);

    //    Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", testResult.isWashCode);
    //    Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), testResult.cost);
    //    Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, testResult.duration);
    }

    @Test
    public void getServiceById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        HttpEntity<List<MoikaService>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<MoikaService>> resultAll = restTemplate.exchange(
                "http://localhost:8080/MoikaService/washFacility/{id}/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<MoikaService>>() {
                }, 8);

        List<MoikaService> serviceList = resultAll.getBody();
        Assert.assertNotNull("Request body is incorrect", resultAll);
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());

        TestResult testResult = fillTestResult( serviceList, 11);

        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", testResult.serviceCode.equalsIgnoreCase("WASH"));
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), testResult.cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, testResult.duration);
    }
}
