package com.example.demo.tables;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
	Customer findCustomerByName (String name);
	boolean existsByName (String name);
	boolean existsByNameAndPassword (String name , String password);
	@Transactional
	  @Modifying
	@Query("DELETE FROM CUSTOMER CUST  WHERE CUST.id  = :customer_id")
	void deleteCustomerById (@Param ("customer_id") long customer_id);
	@Transactional
	  @Modifying
	@Query("DELETE FROM CUSTOMER CUST WHERE CUST.name  = :customer_name")
	void deleteCustomerByName (@Param ("customer_name")String customer_name);
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.type = :coupon_type")
	ArrayList<Coupon> getCouponsByType (@Param ("customer_id")long customer_id , @Param("coupon_type")CouponType couponType);
	
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.price <= :coupon_price")
	ArrayList<Coupon> getCouponsByTopPrice (@Param ("customer_id")long customer_id , @Param("coupon_price")double price);
}
