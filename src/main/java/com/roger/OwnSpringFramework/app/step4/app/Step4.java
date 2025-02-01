package com.roger.OwnSpringFramework.app.step4.app;

import com.roger.OwnSpringFramework.app.step4.app.model.Company;
import com.roger.OwnSpringFramework.app.step4.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step4.framework.ApplicationContext;

public class Step4 {

    public static void main(String[] args) {

        final ApplicationContext applicationContext = new ApplicationContext(Step4.class.getPackage());
        final CompanyService companyService = applicationContext.getBean(CompanyService.class);

        companyService.createCompany(new Company());
    }
}
