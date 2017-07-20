package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.khasang.moika.util.Interval;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * MoikaService - базовая сущность представляющая услуги, оказываемые автомойками
 * связан с
 * -соответствующей автомойкой
 * - типом услуг (мойка, чистка, и т.п.)
 * - статусом (действует, проектируется, не оказывается и т.п.)
 *
 */

@Entity(name = "services")
@TypeDef(name="interval", typeClass = Interval.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class MoikaService extends ABaseMoikaEntity{

    @Id
    @Column(name = "id_service", columnDefinition = "bigserial", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) //не IDENTITY, а тот что в таблицах
    protected long id;

    @Column(name = "id_fclt", insertable = false, updatable = false)
    protected long idFacility;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_fclt", foreignKey = @ForeignKey(name = "fk_service_id_fclt"), insertable = false, updatable = false)
    protected WashFacility washFacility;

    @Column(name = "id_type", insertable = false, updatable = false)
    protected int idType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_type",  foreignKey = @ForeignKey(name ="fk_service_type"))
    protected ServiceType serviceType;

    @Column(name = "id_group", insertable = false, updatable = false)
    protected int idGroup;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_group",  foreignKey = @ForeignKey(name ="fk_service_group"), insertable = false, updatable = false)
    protected ServiceGroup serviceGroup;

    @Column(name = "id_status",insertable = false, updatable = false)
    protected int idStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "fk_service_status"), insertable = false, updatable = false)
    protected ServiceStatus serviceStatus;


    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");

    @Column(name = "duration")
    @Type(type = "interval")
    protected Integer serviceDuration;


    @Column(name = "service_name", unique = true)
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "time_create")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeCreate;

    @Column(name = "time_edit")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeEdit;

    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateStart;

    @Column(name = "date_stop")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateStop;

    @Column(name = "id_user_create",insertable = false, updatable = false)
    protected long idUserCreate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_create", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_user_create"), insertable = false, updatable = false)
    protected User userCreate;

    @Column(name = "id_user_edit",insertable = false, updatable = false)
    protected long idUserEdit;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_edit",  referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_user_edit"), insertable = false, updatable = false)
    protected User userEdit;


    public MoikaService() {
    }


    public long getId() {
        return this.id;
    }

    public void setServiceStatus(short status) {
        this.idStatus = status;
    }

    public void setServiceStatus(String code) {
        ServiceStatus st = new ServiceStatus();
        st.setStatusCode(code);
        this.serviceStatus = st;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setWashFacility(WashFacility washFacility) {
        this.washFacility = washFacility;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(EServiceType idType) {
        this.idType = idType.ordinal();
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }


    public void setIdUserCreate(int idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public void setIdUserEdit(int idUserEdit) {
        this.idUserEdit = idUserEdit;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatusEntity) {
        this.serviceStatus = serviceStatusEntity;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Short idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Integer getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public Date getTimeEdit() {
        return timeEdit;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    public long getIdUserCreate() {
        return idUserCreate;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public long getIdUserEdit() {
        return idUserEdit;
    }

    public User getUserEdit() {
        return userEdit;
    }

    public void setTimeEdit(Date timeEdit) {
        this.timeEdit = timeEdit;
    }

    public void setUserCreate(User userCreate) {
        this.userCreate = userCreate;
    }

    public void setUserEdit(User userEdit) {
        this.userEdit = userEdit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABaseMoikaService)) return false;

        MoikaService that = (MoikaService) o;

        if (getId() != that.getId()) return false;
        if (getIdFacility() != that.getIdFacility()) return false;
        if (idStatus != that.getIdStatus()) return false;
        if (idGroup != that.getIdGroup()) return false;
        if (!timeCreate.equals(that.getTimeCreate())) return false;
        if (!timeEdit.equals(that.getTimeEdit())) return false;
        if (!userCreate.equals(that.getUserCreate())) return false;
        if (!userEdit.equals(that.getUserEdit())) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idFacility, idGroup, idStatus, serviceCost, timeCreate, timeEdit, idUserCreate);
    }

    @Override
    public String toString() {
        return this.getClass().getName()+" {" +
                "id=" + id +
                ", idFacility=" + idFacility +
                ", washFacility=" + washFacility +
                ", serviceGroup=" + idGroup +
                ", idStatus=" + idStatus +
                ", serviceStatusEntity=" + serviceStatus.code +
                ", cost='" + serviceCost.toString() + '\'' +
                ", duration='" + serviceDuration.toString() + '\'' +
                ", serviceName='" + name + '\'' +
                ((description != null) ? ", description='" + description : "") + '\'' +
                '}';
    }
}
