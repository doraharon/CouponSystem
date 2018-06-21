package com.example.demo.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepo extends CrudRepository<Company, Long>{
	Company findCompanyByName (String name);
	boolean existsByName (String name);
	boolean existsByNameAndPassword (String name , String password);

	/*@Query("SELECT coupon FROM COMPANY COMP WHERE COMP.id = : company_id AND COMP.coupons.id IS NOT NULL")
	ArrayList<Coupon> getCompanyCoupons (@Param ("company_id") long company_id);*/
	
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.type = :coupon_type")
	ArrayList<Coupon> getCouponsByType (@Param ("company_id")long company_id , @Param("coupon_type")CouponType couponType);
	
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.price <= :coupon_price")
	ArrayList<Coupon> getCouponsByTopPrice (@Param ("company_id")long company_id , @Param("coupon_price")double price);
	
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.endDate <= :coupon_endDate")
	ArrayList<Coupon> getCouponsByEndDate (@Param ("company_id")long company_id , @Param("coupon_endDate")Date endDate);
	
}
