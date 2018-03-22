package com.dor.coupons.system.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>This CouponRepo is the basic way to communicate with the Coupon table in the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category REPO interface
 */
public interface CouponRepo extends CrudRepository<Coupon, Long>{
	/**
	 * This findCouponByTitle search for a coupon object with the given title and if exists returns it.
	 * @param title The given coupon title you want to get from the database.
	 * @return Coupon object with the given title.
	 */
	Coupon findCouponByTitle (String title);
	/**
	 *<p>This findCouponsByType get collection of all Coupon objects with the given coupon type.</p>
	 * @param couponType Enum of the coupons type you want to get from the database.
	 * @return Collection of all Coupon objects with the given coupon type.
	 */
	@Query(value = "SELECT * FROM COUPON WHERE type=?1",nativeQuery=true)
	ArrayList<Coupon>findCouponByType(CouponType couponType);
	/**
	 * This existsByTitle checks if a Coupon object with the given title exists in the database.
	 * @param title The given coupon title you want to check if exists in the database.
	 * @return Boolean of true if coupon with the given title exists or false if not.
	 */
	boolean existsByTitle (String title);
	/**
	 *<p>This existsByTitleAndCustomers_Id checks if a Coupon object with the given title and customer id exists in the database.</p>
	 *<p>Mainly checks if the Coupon has been purchase by customer with the given id.</p>
	 * @param customerId The id of the customer that purchase the coupon.
	 * @param title The title of the coupon you want to check if exists from the database.
	 * @return Boolean of true if the coupon with the given title and given customer id exists in the database.
	 */
	boolean existsByTitleAndCustomers_Id(String title, long customerId);
	/**
	 * This deleteCouponByTitle delete a coupon by given title from the database. 
	 * @param coupon_title The title of the coupon you want to delete from the database.
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM COUPON COUP WHERE COUP.title  = :coupon_title")
	void deleteCouponByTitle (@Param ("coupon_title")String coupon_title);
	/**
	 *<p>This deleteCouponByEndDate delete all the coupons whose end date is before and same with given date.</p>
	 *<p>Mainly use in the daily thread to delete expired coupons</p>  
	 * @param endDate The expiration date limit of the coupons you want to delete from the database.
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM COUPON COUP WHERE COUP.endDate <= :coupon_endDate")
	void deleteCouponByEndDate (@Param("coupon_endDate") Date endDate);
}