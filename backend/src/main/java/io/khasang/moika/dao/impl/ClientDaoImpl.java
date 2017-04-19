package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.entity.Client;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("clientDao")
public class ClientDaoImpl extends MoikaDaoCrudImpl<Client> implements ClientDao {

    public ClientDaoImpl() {
    }

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
