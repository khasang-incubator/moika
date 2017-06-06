package io.khasang.moika.service.impl;

import io.khasang.moika.dao.ClientStatusDao;
import io.khasang.moika.entity.ClientStatus;
import io.khasang.moika.service.ClientStatusDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "clientStatusDataAccessService")
@Transactional
public class ClientStatusDataAccessServiceImpl extends  AStatusDataAccessServiceImpl<ClientStatus> implements ClientStatusDataAccessService {
    final
    ClientStatusDao clientStatusDao;

    @Autowired()
    public ClientStatusDataAccessServiceImpl(ClientStatusDao clientStatusDao) {
        this.clientStatusDao = clientStatusDao;
        setStatusDao(this.clientStatusDao);
    }

}
