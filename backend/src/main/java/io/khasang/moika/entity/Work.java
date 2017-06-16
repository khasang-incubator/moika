package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Work - сСущность, описывающая работы, которые проводились или в данный момент проводятся конкретным боксом,
 * на конкретной автомойке с конкретным автомобилем, по конкретному заказу
 * Необходимы для учета производительности и в первую голову для управления очередью маошин
 */
@Entity(name = "works")
public class Work extends ABaseMoikaEntity {
    @Id
    @Column(name = "id_work", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.AUTO) //не IDENTITY, а тот что в таблицах
    protected Long idWork;

    @Column(name = "id_order", insertable=false, updatable=false)
    protected Long idOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order" )
    @JsonBackReference
    protected  Orders order;

    @Column(name = "id_box", insertable=false, updatable=false)
    protected Long idBox;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_box" )
    @JsonBackReference
    protected  WashBox washBox;

    @Column(name = "id_manager", insertable=false, updatable=false)
    protected Long idManager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manager" )
    @JsonBackReference
    protected  User manager;

    @Column(name = "id_worker", insertable=false, updatable=false)
    protected Long idWorker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker" )
    @JsonBackReference
    protected  User worker;

    @Column(name = "time_start")
    protected Timestamp timeStart;

    @Column(name = "time_plan_finish")
    protected Timestamp timePlanFinish;

    @Column(name = "time_finish")
    protected Timestamp timeFinish;

    @Column(name = "add_info")
    protected String addInfo;

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public Long getidOrder() {
        return idOrder;
    }

    public void setidOrder(Long idOrder) {
        this.idOrder = idOrder;
    }


    public Long getidBox() {
        return idBox;
    }

    public void setidBox(Long idBox) {
        this.idBox = idBox;
    }

    public Long getidManager() {
        return idManager;
    }

    public void setidManager(Long idManager) {
        this.idManager = idManager;
    }

    public java.sql.Timestamp gettimeStart() {
        return timeStart;
    }

    public void settimeStart(java.sql.Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public java.sql.Timestamp gettimePlanFinish() {
        return timePlanFinish;
    }

    public void settimePlanFinish(java.sql.Timestamp timePlanFinish) {
        this.timePlanFinish = timePlanFinish;
    }

    public java.sql.Timestamp gettimeFinish() {
        return timeFinish;
    }

    public void settimeFinish(java.sql.Timestamp timeFinish) {
        this.timeFinish = timeFinish;
    }

    public Long getidWorker() {
        return idWorker;
    }

    public void setidWorker(Long idWorker) {
        this.idWorker = idWorker;
    }

    public String getaddInfo() {
        return addInfo;
    }

    public void setaddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    @Override
    public String toString() {
        return "Work{" +
                "idWork=" + idWork +
                ", idOrder=" + idOrder +
                ", idBox=" + idBox +
                ", idManager=" + idManager +
                ", idWorker=" + idWorker +
                ", timeStart=" + timeStart +
                ", timePlanFinish=" + timePlanFinish +
                ", timeFinish=" + timeFinish +
                ", addInfo='" + addInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Work work = (Work) o;

        if (!getIdWork().equals(work.getIdWork())) return false;
        if (!idOrder.equals(work.idOrder)) return false;
        if (!idBox.equals(work.idBox)) return false;
        if (!idWorker.equals(work.idWorker)) return false;
        if (!timeStart.equals(work.timeStart)) return false;
        return timePlanFinish != null ? timePlanFinish.equals(work.timePlanFinish) : work.timePlanFinish == null;
    }

    @Override
    public int hashCode() {
        int result = getIdWork().hashCode();
        result = 31 * result + idOrder.hashCode();
        result = 31 * result + idBox.hashCode();
        result = 31 * result + idWorker.hashCode();
        result = 31 * result + timeStart.hashCode();
        return result;
    }
}
