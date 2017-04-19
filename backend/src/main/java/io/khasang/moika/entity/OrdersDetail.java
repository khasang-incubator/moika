package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "order_details")
public class OrdersDetail extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detail")
    private Long id;

    @Column(name = "id_order")
    private Long idOrder;
    @ManyToOne
    @JoinColumn(name = "id_order", insertable=false, updatable=false )
    @JsonBackReference
    private Orders order;

    @Column(name = "id_service")
    private Long idService;
    @ManyToOne
    @JoinColumn(name = "id_service", insertable=false, updatable=false )
    @JsonBackReference
    private MoikaService moikaService;

    @Column(name = "id_work")
    private Long idWork;
    @OneToOne
    @JoinColumn(name = "id_work", foreignKey = @ForeignKey(name = "WORK_ID_FK"), insertable=false, updatable=false)
    private Work work;

    @Column(name = "service_cost")
    private BigDecimal serviceCost;

    public OrdersDetail() {
    }

    public Long getId() {
        return id;
    }


    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public MoikaService getWashservice() {
        return moikaService;
    }

    public void setWashservice(MoikaService moikaService) {
        this.moikaService = moikaService;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", order=" + order.toString() +
                ", idService=" + idService +
                ", service=" + moikaService.toString() +
                ", idWork=" + idWork +
                ", work=" + work.toString()+
                ", serviceCost=" + serviceCost.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdersDetail)) return false;

        OrdersDetail that = (OrdersDetail) o;

        if (!getId().equals(that.getId())) return false;
        if (!getIdOrder().equals(that.getIdOrder())) return false;
        return getIdService().equals(that.getIdService());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getIdOrder().hashCode();
        result = 31 * result + getIdService().hashCode();
        return result;
    }
}
