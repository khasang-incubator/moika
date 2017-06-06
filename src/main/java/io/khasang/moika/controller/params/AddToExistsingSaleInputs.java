package io.khasang.moika.controller.params;

public class AddToExistsingSaleInputs {
    private Long saleId;
    private Long productId;
    private Short productCount;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Short getProductCount() {
        return productCount;
    }

    public void setProductCount(Short productCount) {
        this.productCount = productCount;
    }
}
