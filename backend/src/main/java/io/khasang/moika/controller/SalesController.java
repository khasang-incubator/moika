package io.khasang.moika.controller;

import io.khasang.moika.controller.params.AddToExistsingSaleInputs;
import io.khasang.moika.controller.params.SetSaleStatusInputs;
//import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.dao.ProductSaleDao;
import io.khasang.moika.dao.SaleDao;
import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.Product;
import io.khasang.moika.entity.ProductSale;
import io.khasang.moika.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
public class SalesController {
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private ProductSaleDao productSaleDao;
    @Autowired
    private ProductDao productDao;
    /*@Autowired
    private ClientDao clientDao;*/

    @RequestMapping(value = "/shop/sale/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Sale createNewSale(@RequestBody Map<String, Object> params) {
        short productCount;
        long productId;
        Integer clientId = null;

        try {
            if (params.get("productId") == null) {
                throw new RuntimeException("Param name cannot be null");
            }
            productId = Long.parseLong((String)params.get("productId"));
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format of parameter productId");
        }

        try {
            if (params.get("productCount") == null) {
                throw new RuntimeException("Param name cannot be null");
            }

            productCount = Short.parseShort((String) params.get("productCount"));
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format of parameter productCount");
        }

        if (params.get("clientId") != null) {
            try {
                clientId = Integer.parseInt((String) params.get("clientId"));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format of parameter productCount");
            }
        }

        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product doesn't exist");
        }


        Sale sale = new Sale();
        sale.setCreationDate(new Date());
        sale.setStatus("CREATED");
        sale.setPrice(productCount * product.getPrice());

        /*if (clientId != null) {
            Client client = clientDao.getClientById(clientId);
            sale.setClient(client);
        }*/
        saleDao.addSale(sale);

        ProductSale productSale = new ProductSale();
        productSale.setProduct(product);
        productSale.setProductCount(productCount);
        productSale.setSale(sale);
        productSaleDao.addProductSale(productSale);

        return sale;
    }

    @RequestMapping(value = "/shop/sale/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Sale addToExistsingSale(@RequestBody AddToExistsingSaleInputs inputs) {
        Long saleId = inputs.getSaleId();
        Long productId = inputs.getProductId();
        Short productCount = inputs.getProductCount();

        if (productId == null) {
            throw new RuntimeException("Param productId cannot be null");
        }
        if (saleId == null) {
            throw new RuntimeException("Param saleId cannot be null");
        }
        if (productCount == null) {
            throw new RuntimeException("Param productCount cannot be null");
        }

        ProductSale productSale = productSaleDao.getProductSaleBySaleAndProduct(saleId, productId);
        Sale sale = saleDao.getSaleById(saleId);
        Product product = productDao.getProductById(productId);

        double price = sale.getPrice() + productCount * product.getPrice();

        if (productSale == null) {
            productSale = new ProductSale();
            productSale.setProductCount(productCount);
            productSale.setProduct(productDao.getProductById(productId));
            productSale.setSale(saleDao.getSaleById(saleId));
            productSaleDao.addProductSale(productSale);
        }
        else {
            productCount = (short) (productCount + productSale.getProductCount());
            productSale.setProductCount(productCount);
            productSaleDao.updateProductSale(productSale);
        }

        sale.setPrice(price);
        saleDao.updateSale(sale);

        return sale;
    }

    @RequestMapping(value = "/shop/sale/setstatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Sale setSaleStatus(@RequestBody SetSaleStatusInputs inputs) {
        Sale sale = saleDao.getSaleById(inputs.getSaleId());
        sale.setStatus(inputs.getStatus());
        saleDao.updateSale(sale);
        return sale;
    }
}
