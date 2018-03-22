package com.dor.coupons.system;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.dor.coupons.system.core.ClientType;
import com.dor.coupons.system.core.CouponSystem;
import com.dor.coupons.system.entities.TestsVariables;
import com.dor.coupons.system.exceptions.CompanyExistsException;
import com.dor.coupons.system.exceptions.CompanyNotExistsException;
import com.dor.coupons.system.exceptions.CouponAlreadyPurchasedException;
import com.dor.coupons.system.exceptions.CouponExistsException;
import com.dor.coupons.system.exceptions.CouponExpierdException;
import com.dor.coupons.system.exceptions.CouponNotExistsException;
import com.dor.coupons.system.exceptions.CouponOutOfStockException;
import com.dor.coupons.system.exceptions.CustomerExistsException;
import com.dor.coupons.system.exceptions.CustomerNotExistException;
import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;
import com.dor.coupons.system.facades.AdminFacade;
import com.dor.coupons.system.facades.CompanyFacade;
import com.dor.coupons.system.facades.CustomerFacade;
import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
import com.dor.coupons.system.tables.Customer;

/**
 * This CouponsSystemApplicationTests tests all the facades methods including the exceptions in every method.
 * @author Dor aharon
 * @version 1.0
 * @category TEST class
 */
@DirtiesContext (classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponsSystemApplicationTests {
	@Autowired
	CouponSystem couponSystem;
	SimpleDateFormat endDate = new SimpleDateFormat(TestsVariables.simpleDateFormat);
	/**
	 * <B>Not in use</b>
	 */
	@Test
	public void contextLoads() {
	}
	/**
	 * This test01_adminLogin tests correct login for admin and getting adminFacade in return.
	 */
	@Test
	public void test01_adminLogin()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		assertTrue(adminFacade != null);
	}
	/**
	 * This test02_adminLoginException tests WrongUserOrPasswordException in a case of wrong admin login details. (wrong password in this method)
	 */
	@Test (expected = WrongUserOrPasswordException.class)
	public void test02_adminLoginException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.wrongPassword, ClientType.ADMIN);
	}
	/**
	 * This test03_createCompany tests creating a company object and saving it in the database, checks it by comparing the company from the database to the created one.
	 */
	@Test
	public void test03_createCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Company companyFromDB = adminFacade.getCompanyByName(fox.getName());
		Assert.assertEquals(this.compareCompany(fox,companyFromDB),true);
	}
	/**
	 * This test04_creatCompanyException tests CompanyExistsException by trying to add the same company twice.
	 */
	@Test (expected = CompanyExistsException.class)
	public void test04_creatCompanyException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		adminFacade.createCompany(fox);
	}
	/**
	 * This test05_companyLogin tests correct company login and getting companyFacade in return.
	 */
	@Test
	public void test05_companyLogin()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		assertTrue(companyFacade != null);
	}
	/**
	 *This test06_companyLoginException tests WrongUserOrPasswordException in a case of wrong company login details. (wrong user name in this method)
	 */
	@Test (expected = WrongUserOrPasswordException.class)
	public void test06_companyLoginException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.wrongUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
	}
	/**
	 *<p>This test07_getCompany tests two methods of getting company from the database.</p>
	 *<p>getCompany (by id) and getCompanyByName (by name) and comparing both of them.</p>
	 */
	@Test
	public void test07_getCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Company companyByName = adminFacade.getCompanyByName(fox.getName());
		Company companyById = adminFacade.getCompany(companyByName.getId());
		Assert.assertEquals(this.compareCompany(companyById,companyByName),true);	
	}
	/**
	 * This test08_getCompanyException tests CompanyNotExistsException by trying to get company by id that not exists.
	 */
	@Test (expected = CompanyNotExistsException.class)
	public void test08_getCompanyException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company companyById = adminFacade.getCompany(TestsVariables.wrongIndex);
	}
	/**
	 * This test09_getCompanyByNameException tests CompanyNotExistsException by trying to get company that not exists by name. 
	 */
	@Test (expected = CompanyNotExistsException.class)
	public void test09_getCompanyByNameException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company companyByName = adminFacade.getCompanyByName(TestsVariables.wrongUserName);
	}
	/**
	 *This test10_getAllCompanies tests getting all the companies from the database by comparing the companies from the database to the companies that has been created.
	 */
	@Test
	public void test10_getAllCompanies()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company hot = new Company(TestsVariables.hotCompanyUserName ,TestsVariables.hotCompanyPassword, TestsVariables.hotCompanyEmail);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(hot);
		adminFacade.createCompany(fox);
		List<Company> companies = new ArrayList<>();
		companies.add(hot);
		companies.add(fox);
		List<Company> companiesFromDB = (List<Company>) adminFacade.getAllCompanies();
		assertTrue(companiesFromDB.size() == companies.size());
		for (Company company : companiesFromDB)
		{
			if (company.getName().equals(fox.getName()))
			{
				Assert.assertEquals(this.compareCompany(fox , company),true);
			}
			else
			{
				Assert.assertEquals(this.compareCompany(hot , company),true);
			}
		}
	}
	/**
	 * This test11_updateCompany tests updating company to the database and checks that the details changed in the database.
	 */
	@Test
	public void test11_updateCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		fox.setPassword(TestsVariables.updatedPassword);
		fox.setEmail(TestsVariables.updatedCompanyEmail);
		adminFacade.updateCompany(fox);
		Company companyFromDB = adminFacade.getCompanyByName(fox.getName());
		Assert.assertEquals(this.compareCompany(fox,companyFromDB),true);
	}
	/**
	 * This test12_createCustomer tests creating a customer object and saving it in the database, checks it by comparing the customer from the database to the created one.
	 */
	@Test
	public void test12_createCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		Customer customerFromDB = adminFacade.getCustomerByName(dor.getName());
		Assert.assertEquals(this.compareCustomer(dor,customerFromDB),true);
	}
	/**
	 * This test13_createCustomerException tests CustomerExistsException by trying to add the same customer twice.
	 */
	@Test (expected = CustomerExistsException.class)
	public void test13_createCustomerException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		adminFacade.createCustomer(dor);
	}
	/**
	 * This test14_customerLogin tests correct customer login and getting customerFacade in return.
	 */
	@Test
	public void test14_customerLogin()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		assertTrue(customerFacade != null);
	}
	/**
	 * This test15_customerLoginException tests WrongUserOrPasswordException in a case of wrong customer login details (wrong ClientType in this method)
	 */
	@Test (expected = WrongUserOrPasswordException.class)
	public void test15_customerLoginException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.COMPANY);
	}
	/**
	 *<p>This test16_getCustomer tests two methods of getting customer from the database.</p>
	 *<p>getCustomer (by id) and getCustomerByName (by name) and comparing both of them.</p>
	 */
	@Test 
	public void test16_getCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		Customer customerByName = adminFacade.getCustomerByName(dor.getName());
		Customer customerById = adminFacade.getCustomer(customerByName.getId());
		Assert.assertEquals(this.compareCustomer(customerById,customerByName),true);	
	}
	/**
	 * This test17_getCustomerException tests CustomerNotExistsException by trying to get customer by id that not exists.
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test17_getCustomerException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer customerById = adminFacade.getCustomer(TestsVariables.wrongIndex);
	}
	/**
	 * This test18_getCustomerByNameException tests CustomerNotExistsException by trying to get customer by name that not exists.
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test18_getCustomerByNameException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer customerByName = adminFacade.getCustomerByName(TestsVariables.wrongUserName);
	}
	/**
	 * This test19_getAllCustomers tests getting all the customers from the database by comparing the customers from the database to the customers that has been created. 
	 */
	@Test
	public void test19_getAllCustomers()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		Customer cristina = new Customer(TestsVariables.cristinaCustomerUserName , TestsVariables.cristinaCustomerPassword);
		adminFacade.createCustomer(cristina);
		List<Customer> customers = new ArrayList<>();
		customers.add(dor);
		customers.add(cristina);
		List<Customer> customersFromDB = (List<Customer>) adminFacade.getAllCustomers();
		assertTrue(customersFromDB.size() == customers.size());
		for (Customer customer : customersFromDB)
		{
			if (customer.getName().equals(dor.getName()))
			{
				Assert.assertEquals(this.compareCustomer(dor , customer),true);
			}
			else
			{
				Assert.assertEquals(this.compareCustomer(cristina , customer),true);
			}
		}
	}
	/**
	 * This test20_updateCustomer tests updating customer to the database and checks that the details changed in the database.
	 */
	@Test
	public void test20_updateCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		dor.setPassword(TestsVariables.updatedPassword);
		adminFacade.updateCustomer(dor);
		Customer customerFromDB = adminFacade.getCustomerByName(dor.getName());
		Assert.assertEquals(this.compareCustomer(dor,customerFromDB),true);
	}
	/**
	 * This test21_createCoupon tests creating a coupon object and saving it in the database, checks it by comparing the coupon from the database to the created one.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test21_createCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());	
		Assert.assertEquals(this.compareCoupon(shoes,couponFromDB),true);
	}
	/**
	 * This test22_createCouponException tests CouponExistsException by trying to add the same coupon twice.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test (expected = CouponExistsException.class)
	public void test22_createCouponException() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		companyFacade.createCoupon(shoes);
	}
	/**
	 *<p>This test23_getCoupon tests two methods of getting coupon from the database.</p>
	 *<p>getCoupon (by id) and getCouponByTitle (by title) and comparing both of them.</p>
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test23_getCoupon () throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon couponByTitle = companyFacade.getCouponByTitle(shoes.getTitle());
		Coupon couponById = companyFacade.getCoupon(couponByTitle.getId());
		Assert.assertEquals(this.compareCoupon(couponById,couponByTitle),true);	
	}
	/**
	 * This test24_getCouponException tests CouponNotExistsException by trying to get coupon by id that not exists.
	 */
	@Test (expected = CouponNotExistsException.class)
	public void test24_getCouponException ()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		Coupon couponById = companyFacade.getCoupon(TestsVariables.wrongIndex);
	}
	/**
	 * This test25_getCouponByTitleException tests CouponNotExistsException by trying to get coupon by title that not exists.
	 */
	@Test (expected = CouponNotExistsException.class)
	public void test25_getCouponByTitleException ()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		Coupon couponByTitle = companyFacade.getCouponByTitle(TestsVariables.wrongCouponTitle);
	}
	/**
	 * This test26_updateCoupon tests updating coupon to the database and checks that the details changed in the database.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test26_updateCoupon() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		shoes.setEndDate(endDate.parse(TestsVariables.updatedCouponEndDate));
		shoes.setPrice(TestsVariables.updatedCouponPrice);
		companyFacade.updateCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());
		Assert.assertEquals(this.compareCoupon(shoes,couponFromDB),true);	
	}
	/**
	 * <p>This test27_purchaseCoupon tests purchase a coupon by a customer.</p>
	 * <p>This test also tests getPurchasedCouponByTitle method and compares the coupon from the database to the one that has been purchased.</p>
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test27_purchaseCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		Coupon purchasedCoupon = customerFacade.getPurchasedCouponByTitle(shoes.getTitle());
		Integer shoesAmount = shoes.getAmount();
		shoesAmount--;
		shoes.setAmount(shoesAmount);
		Assert.assertEquals(this.compareCoupon(shoes,purchasedCoupon),true);
	}
	/**
	 * This test28_purchaseCouponBoughtExeption tests CouponAlreadyPurchasedException by trying to purchase the same coupon twice.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test (expected = CouponAlreadyPurchasedException.class)
	public void test28_purchaseCouponBoughtExeption() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(shoes);
	}
	/**
	 * This test29_purchaseCouponExpiredExeption tests CouponExpierdException by trying to purchase an expired coupon. 
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 * 
	 */
	@Test (expected = CouponExpierdException.class)
	public void test29_purchaseCouponExpiredExeption() throws ParseException, CouponExpierdException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		Coupon bbb = new Coupon(TestsVariables.bbbCouponTitle,endDate.parse(TestsVariables.bbbExpiredEndDate), TestsVariables.bbbCouponAmount, CouponType.RESTURANTS, TestsVariables.couponMessage, TestsVariables.bbbCouponPrice, TestsVariables.couponImage);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		companyFacade.createCoupon(bbb);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(bbb);
	}
	/**
	 * This test30_purchaseCouponOutOfStockExeption tests CouponOutOfStockException by trying to purchase a out of stock coupon. (amount = 0)
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test (expected = CouponOutOfStockException.class)
	public void test30_purchaseCouponOutOfStockExeption() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		Coupon shirts = new Coupon(TestsVariables.shirtsCouponTitle, endDate.parse(TestsVariables.shirtsCouponEndDate), TestsVariables.shirtsOutOfStockAmount, CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shirtsCouponPrice , TestsVariables.couponImage);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		companyFacade.createCoupon(shirts);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName, TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shirts);
	}
	/**
	 * This test31_getAllCoupons tests getting all the coupons from the database by comparing the coupons from the database to the coupons that has been created. 
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test31_getAllCoupons() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon(TestsVariables.bbbCouponTitle,endDate.parse(TestsVariables.bbbExpiredEndDate), TestsVariables.bbbCouponAmount, CouponType.RESTURANTS, TestsVariables.couponMessage, TestsVariables.bbbCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon(TestsVariables.shirtsCouponTitle, endDate.parse(TestsVariables.shirtsCouponEndDate), TestsVariables.shirtsOutOfStockAmount, CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shirtsCouponPrice , TestsVariables.couponImage);
		companyFacade.createCoupon(shirts);
		List<Coupon> coupons = new ArrayList<>();
		coupons.add(shoes);
		coupons.add(bbb);
		coupons.add(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getAllCoupons();
		assertTrue(couponsFromDB.size() == coupons.size());
		for (Coupon coupon : couponsFromDB)
		{
			if (coupon.getTitle().equals(shoes.getTitle()))
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(shoes.getTitle())),true);
			}
			else if (coupon.getTitle().equals(bbb.getTitle()))
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(bbb.getTitle())),true);
			}
			else
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(shirts.getTitle())),true);
			}
		}
	}
	/**
	 * This test32_getAllCouponsByType tests getting all the coupons with the given type from the database by comparing the coupons from the database to the given coupon type. 
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test32_getAllCouponsByType() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon(TestsVariables.bbbCouponTitle,endDate.parse(TestsVariables.bbbExpiredEndDate), TestsVariables.bbbCouponAmount, CouponType.RESTURANTS, TestsVariables.couponMessage, TestsVariables.bbbCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon(TestsVariables.shirtsCouponTitle, endDate.parse(TestsVariables.shirtsCouponEndDate), TestsVariables.shirtsOutOfStockAmount, CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shirtsCouponPrice , TestsVariables.couponImage);
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getAllCouponsByType(CouponType.SPORTS);
		for(Coupon coupon : couponsFromDB)
		{
			assertTrue(coupon.getType().equals(CouponType.SPORTS));
		}
	}
	/**
	 * This test33_getAllCouponsByPrice tests getting all the coupons from the database that their price is lower or equals to the given price by comparing the coupons price to the given price.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test33_getAllCouponsByPrice() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon(TestsVariables.bbbCouponTitle,endDate.parse(TestsVariables.bbbExpiredEndDate), TestsVariables.bbbCouponAmount, CouponType.RESTURANTS, TestsVariables.couponMessage, TestsVariables.bbbCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon(TestsVariables.shirtsCouponTitle, endDate.parse(TestsVariables.shirtsCouponEndDate), TestsVariables.shirtsOutOfStockAmount, CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shirtsCouponPrice , TestsVariables.couponImage);
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getCouponsByTopPrice(shoes.getPrice());
		for (Coupon coupon : couponsFromDB)
		{
			assertTrue(coupon.getPrice() <= shoes.getPrice());
		}
	}
	/**
	 * This test34_getAllCouponsByEndDate tests getting all the coupons from the database that their end date is before or equals to the given end date by comparing the coupons end date to the date of today.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test34_getAllCouponsByEndDate() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon(TestsVariables.bbbCouponTitle,endDate.parse(TestsVariables.bbbExpiredEndDate), TestsVariables.bbbCouponAmount, CouponType.RESTURANTS, TestsVariables.couponMessage, TestsVariables.bbbCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon(TestsVariables.shirtsCouponTitle, endDate.parse(TestsVariables.shirtsCouponEndDate), TestsVariables.shirtsOutOfStockAmount, CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shirtsCouponPrice , TestsVariables.couponImage);
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getCouponsByEndDate(new Date());
		for(Coupon coupon : couponsFromDB)
		{
			assertTrue(coupon.getEndDate().before(new Date()));
		}
	}
	/**
	 * This test35_getAllPurchasedCoupons tests getting all the purchased coupons of a certain coupon from the database by comparing the purchased coupons from the database to the coupons that has been created.
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test35_getAllPurchasedCoupons() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon(TestsVariables.tentCouponTitle,endDate.parse(TestsVariables.tentCouponEndDate), TestsVariables.tentCouponAmount, CouponType.CAMPING, TestsVariables.couponMessage, TestsVariables.tentCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(tent);
		Coupon pants = new Coupon(TestsVariables.pantsCouponTitle,endDate.parse(TestsVariables.pantsCouponEndDate), TestsVariables.pantsCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.pantsCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(pants);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(pants);
		customerFacade.purchaseCoupon(tent);
		List<Coupon> coupons = new ArrayList<>();
		coupons.add(tent);
		coupons.add(shoes);
		coupons.add(pants);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getAllPurchasedCoupons();
		assertTrue(couponsFromDB.size() == coupons.size());
		for (Coupon coupon : couponsFromDB)
		{
			if (coupon.getTitle().equals(shoes.getTitle()))
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(shoes.getTitle())),true);
			}
			else if (coupon.getTitle().equals(pants.getTitle()))
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(pants.getTitle())),true);
			}
			else
			{
				Assert.assertEquals(this.compareCoupon(coupon, companyFacade.getCouponByTitle(tent.getTitle())),true);
			}
		}
	}
	/**
	 * 
	 * This test36_getPurchasedCouponsByType tests getting all the purchased coupons of certain customer with the given type from the database by comparing the purchased coupons from the database to the given coupon type. 
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test36_getPurchasedCouponsByType() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon(TestsVariables.tentCouponTitle,endDate.parse(TestsVariables.tentCouponEndDate), TestsVariables.tentCouponAmount, CouponType.CAMPING, TestsVariables.couponMessage, TestsVariables.tentCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(tent);
		Coupon pants = new Coupon(TestsVariables.pantsCouponTitle,endDate.parse(TestsVariables.pantsCouponEndDate), TestsVariables.pantsCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.pantsCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(pants);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(pants);
		customerFacade.purchaseCoupon(tent);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getPurchasedCouponsByType(CouponType.SPORTS);
		for(Coupon coupon : couponsFromDB)
		{
			assertTrue(coupon.getType().equals(CouponType.SPORTS));
		}
	}
	/**
	 * This test37_getPurchasedCouponsByPrice tests getting all the purchased coupons of a certain customer from the database that their price is lower or equals to the given price by comparing the purchased coupons price to the given price. 
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test
	public void test37_getPurchasedCouponsByPrice() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon(TestsVariables.tentCouponTitle,endDate.parse(TestsVariables.tentCouponEndDate), TestsVariables.tentCouponAmount, CouponType.CAMPING, TestsVariables.couponMessage, TestsVariables.tentCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(tent);
		Coupon pants = new Coupon(TestsVariables.pantsCouponTitle,endDate.parse(TestsVariables.pantsCouponEndDate), TestsVariables.pantsCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.pantsCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(pants);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword, ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(pants);
		customerFacade.purchaseCoupon(tent);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getPurchasedCouponsByPrice(pants.getPrice());
		for (Coupon coupon : couponsFromDB)
		{
			assertTrue(coupon.getPrice() <= pants.getPrice());
		}
	}
	/**
	 *<p> This test38_removeCoupon tests removing a coupon from the database by trying to get it from the database after deleting it.</p>
	 *<p> CouponNotExistsException is expected because the coupon not exists after deleting it.</p>
	 * @throws ParseException When setting a new date its ask for it Automatically. <b>Please ignore it</b>
	 */
	@Test (expected = CouponNotExistsException.class)
	public void test38_removeCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword , ClientType.COMPANY);
		Coupon shoes = new Coupon(TestsVariables.shoesCouponTitle, endDate.parse(TestsVariables.shoesCouponEndDate),TestsVariables.shoesCouponAmount , CouponType.SPORTS, TestsVariables.couponMessage, TestsVariables.shoesCouponPrice, TestsVariables.couponImage);
		companyFacade.createCoupon(shoes);
		companyFacade.removeCoupon(shoes);
		companyFacade.getCouponByTitle(shoes.getTitle());
	}
	/**
	 *<p> This test39_removeCustomer tests removing a customer from the database by trying to get it from the database after deleting it.</p>
	 *<p> CustomerNotExistException is expected because the customer not exists after deleting it.</p>
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test39_removeCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Customer dor = new Customer(TestsVariables.dorCustomerUserName , TestsVariables.dorCustomerPassword);
		adminFacade.createCustomer(dor);
		adminFacade.removeCustomer(dor);
		adminFacade.getCustomerByName(dor.getName());
	}
	/**
	 *<p> This test40_removeCompay tests removing a company from the database by trying to get it from the database after deleting it.</p>
	 *<p> CompanyNotExistsException is expected because the company not exists after deleting it.</p>
	 */
	@Test (expected = CompanyNotExistsException.class)
	public void test40_removeCompay()
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(TestsVariables.adminUserName, TestsVariables.adminPassword, ClientType.ADMIN);
		Company fox = new Company(TestsVariables.foxCompanyUserName, TestsVariables.foxCompanyPassword, TestsVariables.foxCompanyEmail);
		adminFacade.createCompany(fox);
		adminFacade.removeCompany(fox);
		adminFacade.getCompanyByName(fox.getName());
	}
	/**
	 * This compareCompany compares two company objects by asserts on every field in the company object.
	 * @param one The first company object to compare.
	 * @param two The second company object to compare.
	 * @return true only if all the assert are passed successfully.
	 */
	public boolean compareCompany (Company one, Company two)
	{
		assertTrue(one.getName().equals(two.getName()));
		assertTrue(one.getPassword().equals(two.getPassword()));
		assertTrue(one.getEmail().equals(two.getEmail()));
		return true;
	}
	/**
	 * This compareCustomer compares two customer objects by asserts on every field in the customer object.
	 * @param one The first customer object to compare.
	 * @param two The second customer object to compare.
	 * @return true only if all the assert are passed successfully.
	 */
	public boolean compareCustomer (Customer one, Customer two)
	{
		assertTrue(one.getName().equals(two.getName()));
		assertTrue(one.getPassword().equals(two.getPassword()));
		return true;
	}
	/**
	 * This compareCoupon compares two coupon objects by asserts on every field in the coupon object.
	 * @param one The first coupon object to compare.
	 * @param two The second coupon object to compare.
	 * @return true only if all the assert are passed successfully.
	 */
	public boolean compareCoupon (Coupon one, Coupon two)
	{
		assertTrue(one.getTitle().equals(two.getTitle()));
		assertTrue(one.getStartDate().equals(two.getStartDate()));
		assertTrue(one.getEndDate().equals(two.getEndDate()));
		assertTrue(one.getAmount().equals(two.getAmount()));
		assertTrue(one.getType().equals(two.getType()));
		assertTrue(one.getMessage().equals(two.getMessage()));
		assertTrue(one.getImage().equals(two.getImage()));
		return true;
	}
}
