package com.roger.OwnSpringFramework;

import com.roger.OwnSpringFramework.app.dao.CompanyDaoImpl;
import com.roger.OwnSpringFramework.app.model.Company;
import com.roger.OwnSpringFramework.app.service.CompanyServiceImpl;

public class OwnSpringFrameworkApplication {

	public static void main(String[] args) {
		final CompanyDaoImpl companyDao = new CompanyDaoImpl();
		final CompanyServiceImpl companyService = new CompanyServiceImpl(companyDao);

		companyService.createCompany(new Company());
	}
}
