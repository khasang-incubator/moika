package io.khasang.moika.service.impl;

import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "washServiceDataAccessService")
@Transactional
public class WashServiceDataAccessServiceImpl extends MoikaServiceDataAccessServiceImpl<WashService> implements WashServiceDataAccessService {
    @Autowired
    WashServiceDao serviceDao;

    public WashServiceDataAccessServiceImpl() {
    }

    @Autowired
    public WashServiceDataAccessServiceImpl( @Qualifier("washServiceDao")WashServiceDao wasServiceDao) {
        super(wasServiceDao);
        this.serviceDao = wasServiceDao;
    }

}
