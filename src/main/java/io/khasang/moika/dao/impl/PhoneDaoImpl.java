package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.PhoneDao;
import io.khasang.moika.entity.Phone;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PhoneDaoImpl extends MoikaDaoCrudImpl<Phone> implements PhoneDao {
    private SessionFactory sessionFactory;

    public PhoneDaoImpl() {
    }


}
