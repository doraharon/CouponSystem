package com.dor.coupons.system.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dor.coupons.system.core.ClientType;
import com.dor.coupons.system.core.CouponSystem;
import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;
import com.dor.coupons.system.facades.AdminFacade;
import com.dor.coupons.system.facades.CompanyFacade;
import com.dor.coupons.system.facades.CustomerFacade;

@Controller
public class LoginServlet {
	@Autowired
	CouponSystem couponSystem;
	@RequestMapping( value = "/loginservlet", 
			method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			@RequestParam String username,
			@RequestParam String password , @RequestParam String type) 
	{
		try {
		ClientType clientType = ClientType.valueOf(type);
		switch (clientType)
		{
		case ADMIN:
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(username, password, clientType);
		request.getSession().setAttribute("adminFacade", adminFacade);
		return "redirect:http://localhost:8080/admin/index.html";
		//return "redirect:http://localhost:4200";
			
		case COMPANY:
			CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(username, password, clientType);
			request.getSession().setAttribute("companyFacade", companyFacade);
			return "redirect:http://localhost:8080/company/index.html";
			//return "redirect:http://localhost:4200";
				
		case CUSTOMER:
			CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(username, password, clientType);
			request.getSession().setAttribute("customerFacade", customerFacade);
			return "redirect:http://localhost:8080/customer/index.html";
			//return "redirect:http://localhost:4200";			
		}
		}
		catch(WrongUserOrPasswordException err) {
			return "redirect:http://localhost:8080/login.html?error";
		}
		return null;
	}
	}

