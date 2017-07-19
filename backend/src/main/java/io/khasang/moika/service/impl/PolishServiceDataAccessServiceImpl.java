package io.khasang.moika.service.impl;

import io.khasang.moika.dao.PolishServiceDao;
import io.khasang.moika.entity.PolishService;
import io.khasang.moika.service.PolishServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "polishServiceDataAccessService")
@Transactional
public class PolishServiceDataAccessServiceImpl extends MoikaServiceDataAccessServiceImpl<PolishService> implements PolishServiceDataAccessService {
    @Autowired
    PolishServiceDao polishServiceDao;


    @Autowired
    public PolishServiceDataAccessServiceImpl(@Qualifier("polishServiceDao") PolishServiceDao polishService) {
        super(polishService);
        this.polishServiceDao =  polishService;
    }

}
