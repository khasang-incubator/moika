package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.ClientStatusDao;
import io.khasang.moika.entity.ClientStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("clientStatusDao")
public class ClientStatusDaoImpl extends AllStatusDaoImpl<ClientStatus>  implements ClientStatusDao {

    public ClientStatusDaoImpl() {
    }

}
