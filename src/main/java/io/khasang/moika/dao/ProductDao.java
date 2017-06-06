package io.khasang.moika.dao;

import io.khasang.moika.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList();
    Product getProductById(long id);
    Product getProductByName(String name);
    void addNewProduct(Product product);
    void updateProduct(Product product);
}
