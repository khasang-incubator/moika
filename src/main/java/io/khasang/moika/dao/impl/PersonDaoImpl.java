package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.PersonDao;
import io.khasang.moika.entity.Person;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PersonDaoImpl extends MoikaDaoCrudImpl<Person> implements PersonDao {
    private SessionFactory sessionFactory;

    public PersonDaoImpl() {
    }


}
