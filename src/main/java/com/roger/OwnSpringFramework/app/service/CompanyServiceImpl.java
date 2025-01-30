package com.roger.OwnSpringFramework.app.service;

import com.roger.OwnSpringFramework.app.dao.CompanyDao;
import com.roger.OwnSpringFramework.app.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyDao companyDao;

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
        }
    }

    @Override
    public void updateCompany(Company company) {

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
