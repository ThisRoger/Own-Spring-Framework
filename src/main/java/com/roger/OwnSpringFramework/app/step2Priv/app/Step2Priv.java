package com.roger.OwnSpringFramework.app.step2Priv.app;

import com.roger.OwnSpringFramework.app.step2Priv.app.dao.CompanyDaoImpl;
import com.roger.OwnSpringFramework.app.step2Priv.app.model.Company;
import com.roger.OwnSpringFramework.app.step2Priv.app.service.CompanyService;
import com.roger.OwnSpringFramework.app.step2Priv.app.service.CompanyServiceImpl;
import com.roger.OwnSpringFramework.app.step2Priv.app.service.MyOwnService;
import com.roger.OwnSpringFramework.app.step2Priv.app.service.MyOwnServiceImpl;
import com.roger.OwnSpringFramework.app.step2Priv.framework.ProxyHandler;

import java.lang.reflect.Proxy;

public class Step2Priv {

	public static void main(String[] args) {
		CompanyService companyService = (CompanyService) Proxy.newProxyInstance(
				Step2Priv.class.getClassLoader(),
				new Class[]{CompanyService.class},
				new ProxyHandler(new CompanyServiceImpl(new CompanyDaoImpl()))
		);

		companyService.createCompany(new Company());


		MyOwnService myOwnService = (MyOwnService) Proxy.newProxyInstance(
				Step2Priv.class.getClassLoader(),
				new Class[]{MyOwnService.class},
				new ProxyHandler(new MyOwnServiceImpl())
		);
		myOwnService.printOutProxyMessage();
	}
}
