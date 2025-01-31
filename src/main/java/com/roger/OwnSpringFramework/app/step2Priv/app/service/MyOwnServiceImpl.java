package com.roger.OwnSpringFramework.app.step2Priv.app.service;

import com.roger.OwnSpringFramework.app.step2Priv.app.dao.CompanyDao;
import com.roger.OwnSpringFramework.app.step2Priv.app.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyOwnServiceImpl implements MyOwnService {

    private static final Logger logger = LoggerFactory.getLogger(MyOwnServiceImpl.class);

    public MyOwnServiceImpl() {
    }

    @Override
    public void printOutProxyMessage() {
        logger.info("MyOwnServiceImpl - printOutProxyMessage");
    }
}
