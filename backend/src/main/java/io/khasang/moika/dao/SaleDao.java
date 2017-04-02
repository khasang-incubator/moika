package io.khasang.moika.dao;

import io.khasang.moika.entity.Sale;

import java.util.List;

public interface SaleDao {
    Sale getSaleById(long id);
    List<Sale> getClientSales(long clientId);
    List<Sale> getClientSalesByStatus(long clientId, String status);
    Sale getClientActiveSale(long clientId);
    List<Long> getProductIdListBySale(long saleId);
    void addSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(Sale sale);
}
