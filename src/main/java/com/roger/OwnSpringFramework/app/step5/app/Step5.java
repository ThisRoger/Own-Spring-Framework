package com.roger.OwnSpringFramework.app.step5.app;

import com.roger.OwnSpringFramework.app.step5.app.model.Company;
import com.roger.OwnSpringFramework.app.step5.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step5.framework.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step5 {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Step5.class);
        final ApplicationContext applicationContext = new ApplicationContext(Step5.class.getPackage());
        final CompanyService companyService = applicationContext.getBean(CompanyService.class);

        Company company = new Company();
        logger.info( companyService.generateToken(company));
        logger.info( companyService.generateToken(company));
    }
}
