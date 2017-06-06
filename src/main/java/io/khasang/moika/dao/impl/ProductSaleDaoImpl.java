package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ProductSaleDao;
import io.khasang.moika.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Transactional
public class ProductSaleDaoImpl implements ProductSaleDao {
    private SessionFactory sessionFactory;

    public ProductSaleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductSale getProductSaleById(long id) {
        return sessionFactory.getCurrentSession().get(ProductSale.class, id);
    }

    @Override
    public List<ProductSale> getProductSalesBySaleId(long saleId) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<ProductSale> productSaleCriteria= builder.createQuery(ProductSale.class);
        Root<ProductSale> productSaleRoot = productSaleCriteria.from(ProductSale.class);
        Join<ProductSale, Sale> saleJoin = productSaleRoot.join(ProductSale_.sale);

        productSaleCriteria.where(builder.equal(saleJoin.get(Sale_.id), saleId));

        return sessionFactory.getCurrentSession().createQuery(productSaleCriteria).getResultList();
    }

    @Override
    public ProductSale getProductSaleBySaleAndProduct(long saleId, long productId) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<ProductSale> productSaleCriteria = builder.createQuery(ProductSale.class);
        Root<ProductSale> productSaleRoot = productSaleCriteria.from(ProductSale.class);
        Join<ProductSale, Sale> saleJoin = productSaleRoot.join(ProductSale_.sale);
        Join<ProductSale, Product> productJoin = productSaleRoot.join(ProductSale_.product);

        productSaleCriteria.select(productSaleRoot);
        productSaleCriteria.where( builder.and(
                builder.equal(productJoin.get(Product_.id), productId),
                builder.equal(saleJoin.get(Sale_.id), saleId)));

        Query<ProductSale> query = sessionFactory.getCurrentSession().createQuery(productSaleCriteria);
        if (query.getResultList().size() == 0) {
            return null;
        }
        else {
            return query.getSingleResult();
        }
    }

    @Override
    public void addProductSale(ProductSale productSale) {
        sessionFactory.getCurrentSession().save(productSale);
    }

    @Override
    public void updateProductSale(ProductSale productSale) {
        sessionFactory.getCurrentSession().update(productSale);
    }

    @Override
    public void deleteProductSale(ProductSale productSale) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(productSale);
        session.flush();
    }
}
