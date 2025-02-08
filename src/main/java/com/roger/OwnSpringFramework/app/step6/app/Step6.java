package com.roger.OwnSpringFramework.app.step6.app;

import com.roger.OwnSpringFramework.app.step6.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step6.framework.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step6 {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Step6.class);
        final ApplicationContext applicationContext = new ApplicationContext(Step6.class.getPackage());
        final CompanyService companyService = applicationContext.getBean(CompanyService.class);
        final CompanyService companyService2 = applicationContext.getBean(CompanyService.class);

        logger.info(String.valueOf(companyService == companyService2));
    }
}
