package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.SaleDao;
import io.khasang.moika.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SaleDaoImpl implements SaleDao {
    private SessionFactory sessionFactory;

    public SaleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Sale getSaleById(long id) {
        return sessionFactory.getCurrentSession().get(Sale.class, id);
    }

    @Override
    public List<Sale> getClientSales(long clientId) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Sale> saleCriteria = builder.createQuery(Sale.class);
        Root<Sale> saleRoot = saleCriteria.from(Sale.class);
        Join<Sale, Client> clientJoin = saleRoot.join(Sale_.client);

        saleCriteria.where(builder.equal(clientJoin.get(Client_.id),clientId));

        return sessionFactory.getCurrentSession().createQuery(saleCriteria).getResultList();
    }

    @Override
    public List<Sale> getClientSalesByStatus(long clientId, String status) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Sale> saleCriteria = builder.createQuery(Sale.class);
        Root<Sale> saleRoot = saleCriteria.from(Sale.class);
        Join<Sale, Client> clientJoin = saleRoot.join(Sale_.client);

        saleCriteria.where(builder.and(
                builder.equal(clientJoin.get(Client_.id),clientId),
                builder.equal(saleRoot.get(Sale_.status),status)));

        return sessionFactory.getCurrentSession().createQuery(saleCriteria).getResultList();
    }

    @Override
    public Sale getClientActiveSale(long clientId) {
        return getClientSalesByStatus(clientId, "CREATED").get(0);
    }

    @Override
    public List<Long> getProductIdListBySale(long saleId) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<ProductSale> productSaleCriteria = builder.createQuery(ProductSale.class);
        Root<ProductSale> productSaleRoot = productSaleCriteria.from(ProductSale.class);
        Join<ProductSale, Sale> saleJoin = productSaleRoot.join(ProductSale_.sale);

        productSaleCriteria.where(builder.equal(saleJoin.get(Sale_.id),saleId));

        List<Long> productIdList = new ArrayList<>();

        for (ProductSale productSale : sessionFactory.getCurrentSession().createQuery(productSaleCriteria).getResultList()) {
            productIdList.add(productSale.getProduct().getId());
        }

        return productIdList;
    }

    @Override
    public void addSale(Sale sale) {
        sessionFactory.getCurrentSession().save(sale);
    }

    @Override
    public void updateSale(Sale sale) {
        sessionFactory.getCurrentSession().update(sale);
    }

    @Override
    public void deleteSale(Sale sale) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(sale);
        session.flush();
    }
}
