package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.ServiceGroupDao;
import io.khasang.moika.entity.ServiceGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository("ServiceGroupDao")
public class ServiceGroupDaoImpl extends AllTypeDaoImpl<ServiceGroup> implements ServiceGroupDao {

    public ServiceGroupDaoImpl() {
    }

}
