package com.dor.coupons.system.dao;

import java.util.Collection;

import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
/**
 * <p>This CompanyDAO interface is for reading and writing to the Coupon table in the database.</p>
 * <p>The interfece has the following methods:</p>
 * @author Dor aharon
 * @version 1.0
 * @category DAO interface
 */
public interface CouponDAO {
	/**
	 * Add a coupon to the database.
	 * @param coupon The coupon object you want to add to the database.
	 */
	void createCoupon (Coupon coupon);
	/**
	 * Delete coupon from the database. 
	 * @param coupon The coupon you want to delete from the database.
	 */
	void removeCoupon (Coupon coupon);
	/**
	 * Update existing coupon in the database.
	 * @param coupon The updated coupon you want to replace in the database.
	 */
	void updateCoupon (Coupon coupon);
	/**
	 * Get a coupon object according to a given id.
	 * @param id The id of the coupon you want to get from the database.
	 * @return Coupon object with the given id.
	 */
	Coupon getCoupon (long id);
	/**
	 * Get all existing coupons from the database.
	 * @return Iterable of all the coupons objects from the database.
	 */
	Iterable<Coupon> getAllCoupons ();
	/**
	 * Get existing coupons from the database according to a given coupon type.
	 * @return Collection of coupons objects from the database with the given coupon type.
	 */
	Collection<Coupon> getCouponByType (CouponType couponType);
}
