package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("moikaServiceDao")
public class MoikaServiceDaoImpl extends AMoikaServiceDaoImpl<MoikaService>  implements MoikaServiceDao<MoikaService> {


    public MoikaServiceDaoImpl() {
    }

    public MoikaServiceDaoImpl(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

}
