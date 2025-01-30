package com.roger.OwnSpringFramework.app.dao;

import com.roger.OwnSpringFramework.app.model.Company;

public interface CompanyDao {

    void createCompany(Company company);

    void updateCompany(Company company);
}
