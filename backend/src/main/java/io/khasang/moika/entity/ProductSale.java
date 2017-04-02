package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "products_sales")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Sale sale;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "product_count")
    private short productCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getProductCount() {
        return productCount;
    }

    public void setProductCount(short productCount) {
        this.productCount = productCount;
    }
}
