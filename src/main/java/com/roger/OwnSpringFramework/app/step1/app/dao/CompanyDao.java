package com.roger.OwnSpringFramework.app.step1.app.dao;

import com.roger.OwnSpringFramework.app.step1.app.model.Company;

public interface CompanyDao {

    void createCompany(Company company);

    void updateCompany(Company company);
}
