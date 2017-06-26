package io.khasang.moika.integration;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.*;

@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
/**
 * Created by pauls on 25.06.2017.
 */
public class WashFacilityCalendarIntegrationTest {

    private HttpHeaders headers;
    private final String requestMapping = "http://localhost:8080/api";
    final int idFclt = 16;
    Date defDate;

    @Ignore
    @Before
    public void initTests() {
        headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println("Tests are beginning...");
        try {
            defDate = DateUtils.parseDate("1990-01-01", "Y-M-D");
        } catch (ParseException e) {
            defDate = new Date();
        }
    }

    @Test
    @Transactional
    public void testGetFcltCalendar(){

        HttpEntity<WashFacility> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashFacilityCalendar>> fcltResponse = restTemplate.exchange(
                requestMapping + "/facilityCalendar/{idFclt}/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashFacilityCalendar>>() {
                }, idFclt);
        Assert.assertNotNull("Request body is incorrect", fcltResponse);
        Assert.assertTrue("Request code not 202 " + fcltResponse.getStatusCode().toString(), fcltResponse.getStatusCode().is2xxSuccessful());

        List<WashFacilityCalendar> resCalendar = fcltResponse.getBody();
        Assert.assertNotNull("Request body does not contain List of WashFacilityCalendar", resCalendar);
        Assert.assertTrue("List of WashFacilityCalendar is empty", resCalendar.isEmpty());
        ;
        List<WorkHours> resHours = null;
        for (WashFacilityCalendar fcltResc : resCalendar) {
            if (fcltResc.getCalendarDate().compareTo(defDate) == 0 ) {
                if (fcltResc.getIdDateType() == 0) {
                    resHours = fcltResc.getWorkHours();
                    break;
                }
            }
        }
        Assert.assertNotNull("Not found default work hours", resHours);
    }
}
