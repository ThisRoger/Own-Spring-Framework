package com.roger.OwnSpringFramework.app.step2Priv.app.dao;

import com.roger.OwnSpringFramework.app.step2Priv.app.model.Company;

public interface CompanyDao {

    void createCompany(Company company);

    void updateCompany(Company company);
}
