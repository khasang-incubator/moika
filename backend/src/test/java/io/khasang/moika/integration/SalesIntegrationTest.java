package io.khasang.moika.integration;

import io.khasang.moika.controller.params.AddToExistsingSaleInputs;
import io.khasang.moika.controller.params.SetSaleStatusInputs;
import io.khasang.moika.entity.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class SalesIntegrationTest {
    long saleId;
    HttpHeaders headers;

    @Before
    public void setUp() {
        saleId = 3;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void createNewSaleTest() {
        Map<String, Object> inputs = new HashMap<>();
        inputs.put("productId", 1);
        inputs.put("productCount", 5);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(inputs, headers);

        RestTemplate restTemplate = new RestTemplate();
        Sale sale = restTemplate.postForEntity("http://localhost:8080/shop/sale/new", httpEntity, Sale.class).getBody();

        Assert.assertNotNull(sale);
        Assert.assertEquals(sale.getStatus(),"CREATED");
        saleId = sale.getId();
    }

    @Test
    public void addToExistsingSaleTest() {
        short productCount = 3;
        long productId = 2;

        AddToExistsingSaleInputs inputs = new AddToExistsingSaleInputs();
        inputs.setSaleId(saleId);
        inputs.setProductId(productId);
        inputs.setProductCount(productCount);

        HttpEntity<AddToExistsingSaleInputs> httpEntity = new HttpEntity<>(inputs, headers);

        RestTemplate restTemplate = new RestTemplate();
        Sale sale = restTemplate.postForEntity("http://localhost:8080/shop/sale/add", httpEntity, Sale.class).getBody();

        Assert.assertNotNull(sale);
        Assert.assertEquals(saleId, sale.getId());
    }

    @Test
    public void setSaleStatusTest() {
        String status = "CREATED";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        SetSaleStatusInputs inputs = new SetSaleStatusInputs();
        inputs.setSaleId(saleId);
        inputs.setStatus(status);

        HttpEntity<SetSaleStatusInputs> httpEntity = new HttpEntity<>(inputs, headers);

        RestTemplate restTemplate = new RestTemplate();
        Sale sale = restTemplate.postForEntity("http://localhost:8080/shop/sale/setstatus", httpEntity, Sale.class).getBody();

        Assert.assertNotNull(sale);
        Assert.assertEquals(saleId, sale.getId());
        Assert.assertEquals(status, sale.getStatus());
    }
}
