package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.entity.BoxStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("boxStatusDao")
public class BoxStatusDaoImpl  extends AllStatusDaoImpl<BoxStatus>  implements BoxStatusDao {

    public BoxStatusDaoImpl() {
    }

}
