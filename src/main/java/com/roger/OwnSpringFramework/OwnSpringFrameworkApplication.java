package com.roger.OwnSpringFramework;

import com.roger.OwnSpringFramework.app.model.Company;
import com.roger.OwnSpringFramework.app.service.CompanyServiceImpl;

public class Step1App {

	public static void main(String[] args) {
		final CompanyServiceImpl companyDao = new CompanyServiceImpl();
		final CompanyServiceImpl companyService = new CompanyServiceImpl(companyDao);

		companyService.createCompany(new Company());
	}
}
