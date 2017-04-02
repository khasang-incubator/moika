package io.khasang.moika.controller.params;

public class SetSaleStatusInputs {
    private long saleId;
    private String status;

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
