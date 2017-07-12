package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("moikaServiceDao")
public class MoikaServiceDaoImpl extends AMoikaServiceDaoImpl<MoikaService>  implements MoikaServiceDao<MoikaService> {


    public MoikaServiceDaoImpl() {
    }

    public MoikaServiceDaoImpl(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }


}
