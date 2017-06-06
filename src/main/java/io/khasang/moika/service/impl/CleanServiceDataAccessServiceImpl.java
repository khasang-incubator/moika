package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CleanServiceDao;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.service.CleanServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "cleanServiceDataAccessService")
@Transactional
public class CleanServiceDataAccessServiceImpl extends MoikaServiceDataAccessServiceImpl<CleanService> implements CleanServiceDataAccessService {
    @Autowired
    CleanServiceDao cleanServiceDao;

    public CleanServiceDataAccessServiceImpl() {
    }

    @Autowired
    public CleanServiceDataAccessServiceImpl(@Qualifier("cleanServiceDao") CleanServiceDao cleanServiceDao) {
        super(cleanServiceDao);
        this.cleanServiceDao = cleanServiceDao;
    }
 
}
