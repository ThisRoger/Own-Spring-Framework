package com.roger.OwnSpringFramework.app.step3.app;

import com.roger.OwnSpringFramework.app.step3.app.model.Company;
import com.roger.OwnSpringFramework.app.step3.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step3.framework.ApplicationContext;

public class Step3 {

    public static void main(String[] args) {

        final ApplicationContext applicationContext = new ApplicationContext(Step3.class.getPackage());
        final CompanyService companyService = applicationContext.getBean(CompanyService.class);


        companyService.createCompany(new Company());
    }
}
