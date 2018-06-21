package com.example.demo.dao;

import java.util.Collection;

import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;

public interface CouponDAO {
	void createCoupon (Coupon coupon);
	void removeCoupon (Coupon coupon);
	void updateCoupon (Coupon coupon);
	Coupon getCoupon (long id);
	Collection<Coupon> getAllCoupon ();
	Collection<Coupon> getCouponByType (CouponType couponType);
}
