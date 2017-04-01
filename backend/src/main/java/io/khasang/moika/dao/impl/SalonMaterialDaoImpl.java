package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.SalonMaterialDao;
import io.khasang.moika.entity.SalonMaterial;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("salonMaterialDao")
public class SalonMaterialDaoImpl extends AllTypeDaoImpl<SalonMaterial>  implements SalonMaterialDao {

    public SalonMaterialDaoImpl() {
    }

}
