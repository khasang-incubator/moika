package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.DirtTypeDao;
import io.khasang.moika.entity.DirtType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("dirtTypeDao")
public class DirtTypeDaoImpl extends AllTypeDaoImpl<DirtType>  implements DirtTypeDao {

    public DirtTypeDaoImpl() {
    }

}
