package com.roger.OwnSpringFramework.app.step2.app;

import com.roger.OwnSpringFramework.app.step2.app.dao.CompanyDaoImpl;
import com.roger.OwnSpringFramework.app.step2.framework.ProxyHandler;
import com.roger.OwnSpringFramework.app.step2.app.model.Company;
import com.roger.OwnSpringFramework.app.step2.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step2.app.service.CompanyServiceImpl;

import java.lang.reflect.Proxy;

public class Step2 {

	public static void main(String[] args) {
		CompanyService companyService = (CompanyService) Proxy.newProxyInstance(
				Step2.class.getClassLoader(),
				new Class[]{CompanyService.class},
				new ProxyHandler(new CompanyServiceImpl(new CompanyDaoImpl()))
		);

		companyService.createCompany(new Company());
	}
}
