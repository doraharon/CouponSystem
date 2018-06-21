package com.example.demo.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CouponClientFacade;
import com.example.demo.facades.CustomerFacade;

@Component
@Scope ("singleton")
public class CouponSystem  {
	@Autowired
	ApplicationContext ctx;
	public CouponSystem () {
		super ();
	}

	
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		switch (clientType)
		{
		case ADMIN:
			AdminFacade adminFacade = ctx.getBean(AdminFacade.class);
			return adminFacade.login(name, password);
		case COMPANY:
			CompanyFacade companyFacade = ctx.getBean(CompanyFacade.class);
			return companyFacade.login(name, password);
		case CUSTOMER:
			CustomerFacade customerFacade = ctx.getBean(CustomerFacade.class);
			return customerFacade.login(name, password);
		default: return null;
		}
	}
	
	
}
