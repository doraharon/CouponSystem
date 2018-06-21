package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CustomerFacade;
import com.example.demo.system.ClientType;
import com.example.demo.system.CouponSystem;
import com.example.demo.systemExeptions.CouponExistsException;
import com.example.demo.systemExeptions.CouponExpierdException;
import com.example.demo.tables.Company;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;
import com.example.demo.tables.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests {
	@Autowired
	CouponSystem couponSystem;
	@Test
	public void contextLoads() {
	}
	@Test
	public void test1() throws ParseException
	{
		SimpleDateFormat endDate = new SimpleDateFormat("dd/MM/yyyy");
		
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company fox = new Company("Fox" , "4321", "Fox@gmail.com"); 
		adminFacade.createCompany(fox);
		
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("Fox","4321" , ClientType.COMPANY);
	
		Coupon c = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTS, "discount", 30, "photo");
		companyFacade.createCoupon(c);
	

	Coupon d = new Coupon("shoes", endDate.parse("25/08/2018"), 20, CouponType.SPORTS, "discount", 25, "photo");
	Coupon e1 = new Coupon("shirts", endDate.parse("22/04/2019"), 10, CouponType.SPORTS, "discount", 20, "photo");
		companyFacade.createCoupon(d);
		companyFacade.createCoupon(e1);
		try
		{
			companyFacade.createCoupon(e1);
		}
		catch (CouponExistsException e)
		{
			e.printStackTrace();
		}
		//companyFacade.removeCoupon(c);
	
		System.out.println(companyFacade.getCouponByEndDate(endDate.parse("24/08/2018")));
		//adminFacade.removeCompany(fox);
		Customer dor = new Customer("Dor Aharon", "king");
		adminFacade.createCustomer(dor);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("Dor Aharon", "king", ClientType.CUSTOMER);
	
	//	System.out.println(companyFacade.getCouponByTopPrice(25));
			
		//System.out.println(companyFacade.getCouponByEndDate(new GregorianCalendar(2019, 06, 15)));
		//System.out.println(companyFacade.getAllCouponByType(CouponType.HEALTH));
		try
		{
		customerFacade.purchaseCoupon(c);
		}
		catch (CouponExpierdException e) {
			// TODO: handle exception
		}
		//adminFacade.removeCustomer(dor);
	}		
		

	

}
