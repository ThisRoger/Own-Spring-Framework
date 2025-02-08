package com.roger.OwnSpringFramework.app.step5.app.service;

import com.roger.OwnSpringFramework.app.step5.app.model.Company;

public interface CompanyService {

    void createCompany(Company company);

    void updateCompany(Company company);

    String generateToken(Company company);

}
