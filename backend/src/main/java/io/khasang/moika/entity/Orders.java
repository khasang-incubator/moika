package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "orders")
public class Orders extends ABaseMoikaEntity {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.AUTO) //не IDENTITY, а тот что в таблицах
    private Long id;

    @Column(name = "id_fclt")
    private Long idFclt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fclt", insertable=false, updatable=false )
    @JsonBackReference
    private WashFacility washFacility;

    @Column(name = "id_car")
    private Long idCar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", insertable=false, updatable=false )
    @JsonBackReference
    private Car car;

    @Column(name = "id_client")
    private Long idClient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", insertable=false, updatable=false )
    @JsonBackReference
    private Client client;

    @Column(name = "id_discount")
    private Long idDiscount;
    //TODO вставить entity для дисконта

    @Column(name = "add_info")
    private String addInfo;

    @Column(name = "start_sum")
    private BigDecimal startSum;
    @Column(name = "discount_sum")
    private BigDecimal discountSum;
    @Column(name = "final_sum")
    private BigDecimal finalSum;

    @Column(name = "is_prepaid")
    private Boolean isPrepaid;
    @Column(name = "is_canceled")
    private Boolean isCanceled;

    @Column(name = "time_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreate;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Column(name = "time_shedule")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeShedule;

    @Column(name = "time_canceled")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCanceled;

    @Column(name = "time_finish")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFinished;

    @Column(name = "time_payed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePayed;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SELECT)
    private List<OrdersDetail> ordersDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }


    public Long getIdFclt() {
        return idFclt;
    }

    public void setIdFclt(Long idFclt) {
        this.idFclt = idFclt;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Long idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public BigDecimal getStartSum() {
        return startSum;
    }

    public void setStartSum(BigDecimal startSum) {
        this.startSum = startSum;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }

    public BigDecimal getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(BigDecimal finalSum) {
        this.finalSum = finalSum;
    }

    public Boolean getPrepaid() {
        return isPrepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        isPrepaid = prepaid;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setWashFacility(WashFacility washFacility) {
        this.washFacility = washFacility;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTimeShedule() {
        return timeShedule;
    }

    public void setTimeShedule(Date timeShedule) {
        this.timeShedule = timeShedule;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeCanceled() {
        return timeCanceled;
    }

    public void setTimeCanceled(Date timeCanceled) {
        this.timeCanceled = timeCanceled;
    }

    public Date getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Date timeFinished) {
        this.timeFinished = timeFinished;
    }

    public Date getTimePayed() {
        return timePayed;
    }

    public void setTimePayed(Date timePayed) {
        this.timePayed = timePayed;
    }

    public List<OrdersDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", idFclt=" + idFclt +
                ", idCar=" + idCar +
                ", idClient=" + idClient +
                ", idDiscount=" + idDiscount +
                ", addInfo='" + addInfo + '\'' +
                ", startSum=" + startSum.toString() +
                ", discountSum=" + discountSum.toString() +
                ", finalSum=" + finalSum.toString() +
                ", isPrepaid=" + isPrepaid.toString() +
                ", isCanceled=" + isCanceled.toString()+
                ", timeCreate=" + timeCreate.toString()  +
                ", timeStart=" + timeShedule.toString() +
                ", timeStart=" + timeStart.toString()  +
                ", timeCanceled=" + timeCanceled.toString()  +
                ", timeFinished=" + timeFinished.toString()  +
                ", timePayed=" + timePayed .toString() +
                ", ordersDetails=" + ordersDetails.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;

        Orders orders = (Orders) o;

        if (!getId().equals(orders.getId())) return false;
        if (!getIdFclt().equals(orders.getIdFclt())) return false;
        if (!getIdCar().equals(orders.getIdCar())) return false;
        if (!getIdClient().equals(orders.getIdClient())) return false;
        if (getTimeShedule() != null ? !getTimeShedule().equals(orders.getTimeShedule()) : orders.getTimeShedule() != null)
            return false;
        return getTimeCreate().equals(orders.getTimeCreate());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getIdFclt().hashCode();
        result = 31 * result + getIdCar().hashCode();
        result = 31 * result + getIdClient().hashCode();
        result = 31 * result + (getTimeShedule() != null ? getTimeShedule().hashCode() : 0);
        result = 31 * result + getTimeCreate().hashCode();
        return result;
    }
}
