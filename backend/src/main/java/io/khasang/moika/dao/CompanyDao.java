package io.khasang.moika.dao;

import io.khasang.moika.entity.Butterfly;
import io.khasang.moika.entity.Company;

import java.util.List;

public interface CompanyDao extends IMoikaDaoCrud<Company>{

    Company getCompanyByName(String name);

    List<Company> getCompanyHqlList();

    Butterfly getButterflyByName(String butterfly);
}
