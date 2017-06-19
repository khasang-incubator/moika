package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("company")
public class CompanyDaoImpl extends MoikaDaoCrudImpl<Company> implements CompanyDao {

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }

    @Override
    public List<Company> getCompanyHqlList() {
        return (List<Company>) sessionFactory.getCurrentSession().createQuery("FROM company").list();
    }

}
