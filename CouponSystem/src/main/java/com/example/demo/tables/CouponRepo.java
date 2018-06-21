package com.example.demo.tables;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CouponRepo extends CrudRepository<Coupon, Long>{
	Coupon findCouponByTitle (String title);
	ArrayList<Coupon>findCouponByType(CouponType couponType);
	boolean existsByTitle (String title);
	boolean existsByTitleAndCustomers_Id(String title, long id);
	  @Transactional
	  @Modifying
	@Query("DELETE FROM COUPON COUP WHERE COUP.title  = :coupon_title")
	void deleteCouponByTitle (@Param ("coupon_title")String coupon_title);
}