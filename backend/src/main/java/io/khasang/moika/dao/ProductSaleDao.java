package io.khasang.moika.dao;

import io.khasang.moika.entity.ProductSale;

import java.util.List;

public interface ProductSaleDao {
    List<ProductSale> getProductSalesBySaleId(long saleId);
    ProductSale getProductSaleById(long id);
    ProductSale getProductSaleBySaleAndProduct(long saleId, long productId);
    void addProductSale(ProductSale productSale);
    void updateProductSale(ProductSale productSale);
    void deleteProductSale(ProductSale productSale);
}
