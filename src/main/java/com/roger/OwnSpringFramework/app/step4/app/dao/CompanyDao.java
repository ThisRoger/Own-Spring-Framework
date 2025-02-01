package com.roger.OwnSpringFramework.app.step4.app.dao;

import com.roger.OwnSpringFramework.app.step4.app.model.Company;

public interface CompanyDao {

    void createCompany(Company company);

    void updateCompany(Company company);
}
