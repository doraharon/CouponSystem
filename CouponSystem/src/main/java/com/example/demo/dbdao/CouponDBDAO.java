package com.example.demo.dbdao;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dao.CouponDAO;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;
import com.example.demo.tables.CouponRepo;

@Component
@Scope ("singleton")
public class CouponDBDAO implements CouponDAO{
	
	
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CompanyDBDAO companyDBDAO;
	
	
	public CouponDBDAO() {
		super();
	}
	@Override
	public void createCoupon(Coupon coupon) {
		//TODO Auto-generated method stub
		
		couponRepo.save(coupon);
		
	}

	@Override
	public void removeCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponRepo.deleteCouponByTitle(coupon.getTitle());
	}

	@Override
	public void updateCoupon(Coupon coupon)  {
		// TODO Auto-generated method stub

		couponRepo.save(coupon);

	}

	@Override
	public Coupon getCoupon(long id) {
		// TODO Auto-generated method stub
		return couponRepo.findOne(id);			
	}
	public Coupon getCouponByTitle (String title)
	{
		return couponRepo.findCouponByTitle(title);
	}
	@Override
	public ArrayList<Coupon> getAllCoupon()  {
		// TODO Auto-generated method stub
		return (ArrayList<Coupon>) couponRepo.findAll();
	}

	@Override
	public ArrayList<Coupon> getCouponByType(CouponType couponType)  {
		
	// TODO Auto-generated method stub
		return couponRepo.findCouponByType(couponType);
		 
	}
		
	public boolean checkIfBoughtCoupon (String title, long id)
	{
		return couponRepo.existsByTitleAndCustomers_Id(title, id);
	}
	public Boolean checkCouponExistsBytitle(String title)
	{
		return couponRepo.existsByTitle(title);
	}
	
	
}
