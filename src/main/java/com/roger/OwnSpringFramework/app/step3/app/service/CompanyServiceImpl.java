package com.roger.OwnSpringFramework.app.step3.app.service;

import com.roger.OwnSpringFramework.app.step3.app.dao.CompanyDao;
import com.roger.OwnSpringFramework.app.step3.app.model.Company;
import com.roger.OwnSpringFramework.app.step3.framework.annotation.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public void createCompany(Company company) {

        logger.info("SERVICE: Start - create company");
        companyDao.createCompany(company);
        logger.info("SERVICE: END - create company");
    }

    @Override
    public void updateCompany(Company company) {
        logger.info("SERVICE: START - update company");
        companyDao.updateCompany(company);
        logger.info("SERVICE: END - update company");

    }
}
