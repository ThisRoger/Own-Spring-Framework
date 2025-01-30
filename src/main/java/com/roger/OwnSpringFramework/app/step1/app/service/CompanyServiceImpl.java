package com.roger.OwnSpringFramework.app.step1.app.service;

import com.roger.OwnSpringFramework.app.step1.app.dao.CompanyDao;
import com.roger.OwnSpringFramework.app.step1.app.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public void createCompany(Company company) {
        try {
            beginTransaction();
            logger.info("SERVICE: Start - create company");
            companyDao.createCompany(company);
            logger.info("SERVICE: END - create company");
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    @Override
    public void updateCompany(Company company) {
        try {
            beginTransaction();
            logger.info("SERVICE: START - update company");
            companyDao.updateCompany(company);
            logger.info("SERVICE: END - update company");
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }

    }

    private void beginTransaction() {
        logger.debug("BEGIN TRANSACTION");
    }

    private void commitTransaction() {
        logger.debug("COMMIT TRANSACTION");
    }

    private void rollbackTransaction() {
        logger.error("ROLLBACK TRANSACTION");
    }
}
