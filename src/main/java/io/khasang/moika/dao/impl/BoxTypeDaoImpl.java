package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.entity.BoxType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository("boxTypeDao")
public class BoxTypeDaoImpl extends AllTypeDaoImpl<BoxType> implements BoxTypeDao {

    public BoxTypeDaoImpl() {
    }

}
