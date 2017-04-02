package io.khasang.moika.integration;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@Transactional
public class ProductsIntegrationTest {

    @Test
    @Rollback
    public void addNewProductTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Product product = new Product();
        product.setName("Lighter");
        product.setPrice(20.00);
        product.setActive(true);

        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);
        RestTemplate restTemplate = new RestTemplate();

        Product resultProduct = restTemplate.exchange("http://localhost:8080/shop/products/add",
                HttpMethod.POST, httpEntity, Product.class).getBody();

        Assert.assertNotNull(resultProduct);
        Assert.assertEquals(product.getName(), resultProduct.getName());
        Assert.assertEquals(product.getPrice(), resultProduct.getPrice(), 0.1);
        Assert.assertEquals(product.isActive(), resultProduct.isActive());
    }
}
