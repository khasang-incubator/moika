package io.khasang.moika.controller;

import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/shop/products", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    @RequestMapping(value = "/shop/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable(value = "id") String id) {
        return productDao.getProductById(Long.parseLong(id));
    }

    @RequestMapping(value = "/shop/products/byname", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductByName(@RequestParam("name") String name) {
        return productDao.getProductByName(name);
    }

    @RequestMapping(value = "/shop/products/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Product addNewProduct(@RequestBody Product product) {
        product.setActive(true);
        productDao.addNewProduct(product);
        return product;
    }

    @RequestMapping(value = "/shop/products/changeactive", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void changeActive(@RequestParam("productId") String productId, @RequestParam("active") boolean active) {
        long id = Long.parseLong(productId);
        Product product = productDao.getProductById(id);
        product.setActive(active);
        productDao.updateProduct(product);
    }

    @RequestMapping(value = "/shop/products/changeprice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void changeProductPriceById(@RequestParam("productId") String productId,
                                       @RequestParam("price") String price) {
        long id = Long.parseLong(productId);
        double productPrice = Double.parseDouble(price);
        Product product = productDao.getProductById(id);
        product.setPrice(productPrice);
        productDao.updateProduct(product);
    }

}