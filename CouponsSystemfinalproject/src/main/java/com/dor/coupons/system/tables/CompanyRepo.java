package com.dor.coupons.system.tables;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/**
 * <p>This CompanyRepo is the basic way to communicate with the Company table in the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category REPO interface
 */
public interface CompanyRepo extends CrudRepository<Company, Long>{
	/**
	 * This findCompanyByName search for a company object with the given name and if exists returns it.
	 * @param name The given company name you want to get from the database.
	 * @return Company object with the given name.
	 */
	@Query(value = "SELECT * FROM COMPANY WHERE name = :name",nativeQuery=true)
	Company findCompanyByName (@Param("name") String name);
	/**
	 * This existsByName checks if a Company object with the given name exists in the database.
	 * @param name The given company name you want to check if exists in the database.
	 * @return Boolean of True if company with the given name exists or false if not.
	 */
	boolean existsByName (String name);
	/**
	 *<p> This existsByNameAndPassword checks if a Company object with the given name and password exists in the database.</p>
	 *<p> Main use in login method.</p>
	 * @param name The given company name you want to check if exists in the database.
	 * @param password The given company password you want to check if exists in the database.
	 * @return Boolean of true if company with the given name and password exists or false if not. 
	 */
	boolean existsByNameAndPassword (String name , String password);
	/**
	 *<p>This getCouponByCompanyIdAndCouponId get Coupon object with the given coupon id.</p>
	 *<p>The coupon belongs to the company with the given company id.</p>
	 * @param company_id The id of the company that the coupon belong to her.
	 * @param coupon_id The id of the coupon you want to get from the database.
	 * @return Coupon object with the given coupon id that belongs to the company with the given company id.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.id = :coupon_id")
	Coupon getCouponByCompanyIdAndCouponId (@Param ("company_id")long company_id , @Param("coupon_id")long coupon_id);
	/**
	 *<p>This getCouponByCompanyIdAndCouponTitle get Coupon object with the given coupon title.</p>
	 *<p>The coupon belongs to the company with the given company id.</p>
	 * @param company_id The id of the company that the coupon belongs to her.
	 * @param coupon_title The title of the coupon you want to get from the database.
	 * @return Coupon object with the given coupon title that belongs to the company with the given company id.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.title = :coupon_title")
	Coupon getCouponByCompanyIdAndCouponTitle (@Param ("company_id")long company_id , @Param("coupon_title")String coupon_title);
	/**
	 *<p>This getCouponsByType get collection of all Coupon objects with the given coupon type.</p>
	 *<p>The coupons belong to the company with the given company id.</p>
	 * @param company_id The id of the company that the coupons belong to her.
	 * @param couponType Enum of the coupons type you want to get from the database.
	 * @return Collection of all Coupon objects with the given coupon type that belongs to the company with the given company id.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.type = :coupon_type")
	Collection<Coupon> getCouponsByType (@Param ("company_id")long company_id , @Param("coupon_type")CouponType couponType);
	/**
	 *<p>This getCouponsByTopPrice get collection of all Coupon objects whose price is lower or equal to a given price.</p>
	 *<p>The coupons belong to the company with the given company id.</p>
	 * @param company_id The id of the company that the coupons belong to her.
	 * @param coupon_price The price limit of the coupons you want to get from the database.
	 * @return Collection of all Coupon objects whose price is lower or equal to a given price and belongs to the company with the given company id.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.price <= :coupon_price")
	Collection<Coupon> getCouponsByTopPrice (@Param ("company_id")long company_id , @Param("coupon_price")double coupon_price);
	/**
	 *<p>This getCouponsByEndDate get collection of all Coupon objects whose end date is before and same with given date.</p>
	 *<p>The coupons belong to the company with the given company id.</p>
	 * @param company_id The id of the company that the coupons belong to her.
	 * @param coupon_endDate The expiration date limit of the coupons you want to get from the database.
	 * @return Collection of all Coupon objects whose end date is before and same with given date and belongs to the company with the given company id.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.endDate <= :coupon_endDate")
	Collection<Coupon> getCouponsByEndDate (@Param ("company_id")long company_id , @Param("coupon_endDate")Date coupon_endDate);
}
