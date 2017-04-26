package io.khasang.moika.entity;

import io.khasang.moika.util.Interval;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Базовый класс-entity для услуг
 */

@Entity(name = "services")
@TypeDef(name="interval", typeClass = Interval.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_code")
public class MoikaService extends ABaseMoikaEntity{

    @Id
    @Column(name = "id_service", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO) //не IDENTITY, а тот что в таблицах
    protected long id;

    @Column(name = "id_fclt", insertable = false, updatable = false)
    protected long idFacility;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fclt", foreignKey = @ForeignKey(name = "fk_service_id_fclt"), insertable = false, updatable = false)
    protected WashFacility washFacility;

    @Column(name = "type_code", insertable = false, updatable = false)
    protected String serviceTypeCode;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "type_code", foreignKey = @ForeignKey(name = "fk_service_type_code"), insertable = false, updatable = false)
    protected ServiceType serviceTypeEntity;

    @Column(name = "id_subtype", insertable = false, updatable = false)
    protected int idSubType;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_subtype", insertable = false, updatable = false)
    protected ServiceSubType serviceSubTypeEntity;

    @Column(name = "id_status",insertable = false, updatable = false)
    protected int idStatus;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "fk_service_status"), insertable = false, updatable = false)
    protected ServiceStatus serviceStatusEntity;

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

    @Column(name = "id_user_create",insertable = false, updatable = false)
    protected int idUserCreate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_create", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_user_create"), insertable = false, updatable = false)
    protected User userCreate;

    @Column(name = "id_user_edit",insertable = false, updatable = false)
    protected int idUserEdit;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_edit",  referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_user_edit"), insertable = false, updatable = false)
    protected User userEdit;


    public MoikaService() {
    }

    protected MoikaService(String serviceTypeCode) {
        this.serviceTypeCode=serviceTypeCode;
    }


    public long getId() {
        return this.id;
    }


    public String getServiceType() {
        return serviceTypeCode;
    }


    public void setServiceType(String code) {
        ServiceType sse = new ServiceType();
        sse.getTypeCode();
        this.serviceTypeEntity = sse;
        this.serviceTypeCode = serviceTypeEntity.getTypeCode();
    }

    public int getServiceStatus() {
        return idStatus;
    }

    public void setServiceStatus(short status) {
        this.idStatus = status;
    }

    public void setServiceStatus(String code) {
        ServiceStatus st = new ServiceStatus();
        st.setStatusCode(code);
        this.serviceStatusEntity = st;
    }

    public String getServiceName() {
        return name;
    }

    public void setServiceName(String serviceName) {
        this.name = name;
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

    public ServiceType getServiceTypeEntity() {
        return serviceTypeEntity;
    }

    public void setServiceTypeEntity(ServiceType serviceTypeEntity) {
        this.serviceTypeEntity = serviceTypeEntity;
    }

    public ServiceStatus getServiceStatusEntity() {
        return serviceStatusEntity;
    }

    public void setServiceStatusEntity(ServiceStatus serviceStatusEntity) {
        this.serviceStatusEntity = serviceStatusEntity;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
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


    public int getIdSubType() {
        return idSubType;
    }

    public ServiceSubType getServiceSubType() {
        return serviceSubTypeEntity;
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public Integer getServiceDuration() {
        return serviceDuration;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public Date getTimeEdit() {
        return timeEdit;
    }

    public int getIdUserCreate() {
        return idUserCreate;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public int getIdUserEdit() {
        return idUserEdit;
    }

    public User getUserEdit() {
        return userEdit;
    }

    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
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
        if (!serviceTypeCode.equals(that.getServiceTypeCode())) return false;
        if (idStatus != that.getIdStatus()) return false;
        if (!timeCreate.equals(that.getTimeCreate())) return false;
        if (!timeEdit.equals(that.getTimeEdit())) return false;
        if (!userCreate.equals(that.getUserCreate())) return false;
        if (!userEdit.equals(that.getUserEdit())) return false;
        return getServiceName() != null ? getServiceName().equals(that.getServiceName()) : that.getServiceName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getIdFacility() ^ (getIdFacility() >>> 32));
        result = 31 * result + getServiceTypeCode().hashCode();
        result = 31 * result + getIdSubType();
        result = 31 * result + getIdStatus();
        result = 31 * result + getServiceCost().hashCode();
        result = 31 * result + (getServiceDuration() != null ? getServiceDuration().hashCode() : 0);
        result = 31 * result + (getTimeCreate() != null ? getTimeCreate().hashCode() : 0);
        result = 31 * result + (getTimeEdit() != null ? getTimeEdit().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+" {" +
                "id=" + id +
                ", idFacility=" + idFacility +
                ", washFacility=" + washFacility +
                ", serviceTypeCode=" + serviceTypeCode +
                ", idStatus=" + idStatus +
                ", serviceStatusEntity=" + serviceStatusEntity.code +
                ", cost='" + serviceCost.toString() + '\'' +
                ", duration='" + serviceDuration.toString() + '\'' +
                ", serviceName='" + name + '\'' +
                ((description != null) ? ", description='" + description : "") + '\'' +
                '}';
    }
}
