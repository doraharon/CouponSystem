package com.example.demo.dbdao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dao.CompanyDAO;
import com.example.demo.tables.Company;
import com.example.demo.tables.CompanyRepo;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;

@Component
@Scope ("singleton")
public class CompanyDBDAO implements CompanyDAO{
	@Autowired
	CompanyRepo companyRepo;
	private long connectedId;
	
	public CompanyDBDAO() {
		super();
	}
	
	public long getConnectedId() {
		return connectedId;
	}

	public Company getConnectedCompany() {
		return companyRepo.findOne(this.connectedId);
	}

	public void setConnectedId(long connectedId) {
		this.connectedId = connectedId;
	}
	
	@Override
	public void createCompany(Company company){
		// TODO Auto-generated method stub
		companyRepo.save(company);	
	}
	@Override
	public void removeCompany(Company company) {
		// TODO Auto-generated method stub
		
		companyRepo.delete(companyRepo.findCompanyByName(company.getName()));
	}

	@Override
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		
			companyRepo.save(company);
	}

	@Override
	public Company getCompany(long id) {
		// TODO Auto-generated method stub
		return companyRepo.findOne(id);	
	}
	public Company getCompanyByName (String name)
	{
		return companyRepo.findCompanyByName(name);
	}

	@Override
	public ArrayList<Company> getAllCompany() {
		// TODO Auto-generated method stub
		return (ArrayList<Company>)companyRepo.findAll();
	}
	
	public boolean checkCompanyExistsByName (String name)
	{
		return companyRepo.existsByName(name);
	}
	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		Company company = this.getConnectedCompany();
		return company.getCoupons();
	}
	public ArrayList<Coupon> getCouponsByType (CouponType couponType)
	{
		return companyRepo.getCouponsByType(this.connectedId, couponType);
	}
	public ArrayList<Coupon> getCouponsByTopPrice (double price)
	{
		return companyRepo.getCouponsByTopPrice(this.connectedId, price);
	}
	public ArrayList<Coupon> getCouponsByEndDate (Date endDate)
	{
		return companyRepo.getCouponsByEndDate(this.connectedId , endDate);
	}
	@Override
	public boolean login(String companyName, String password) 
	{
		// TODO Auto-generated method stub
	 boolean connected = companyRepo.existsByNameAndPassword(companyName, password);
	 if (connected)
	 {
		 this.connectedId = companyRepo.findCompanyByName(companyName).getId();
		 return true;
	 }
	 return false;
	}
	
}
