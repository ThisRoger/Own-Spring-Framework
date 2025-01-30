package com.roger.OwnSpringFramework.app.step1.app;

import com.roger.OwnSpringFramework.app.step1.app.dao.CompanyDaoImpl;
import com.roger.OwnSpringFramework.app.step1.app.model.Company;
import com.roger.OwnSpringFramework.app.step1.app.service.CompanyServiceImpl;

public class Step1 {

	public static void main(String[] args) {
		final CompanyDaoImpl companyDao = new CompanyDaoImpl();
		final CompanyServiceImpl companyService = new CompanyServiceImpl(companyDao);

		companyService.createCompany(new Company());
	}
}
