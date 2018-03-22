package com.dor.coupons.system.tables;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>This CustomerRepo is the basic way to communicate with the Customer table in the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category REPO interface
 */
public interface CustomerRepo extends CrudRepository<Customer, Long> {
	/**
	 * This findCustomerByName search for a customer object with the given name and if exists returns it.
	 * @param name The given customer name you want to get from the database.
	 * @return Customer object with the given name.
	 */
	@Query(value = "SELECT * FROM CUSTOMER WHERE name=?1",nativeQuery=true)
	Customer findCustomerByName (String name);
	/**
	 * This existsByName checks if a Customer object with the given name exists in the database.
	 * @param name The given customer name you want to check if exists in the database.
	 * @return Boolean of True if customer with the given name exists or false if not.
	 */
	boolean existsByName (String name);
	/**
	 *<p> This existsByNameAndPassword checks if a Customer object with the given name and password exists in the database.</p>
	 *<p> Mainly use in login method.</p>
	 * @param name The given customer name you want to check if exists in the database.
	 * @param password The given customer password you want to check if exists in the database.
	 * @return Boolean of true if customer with the given name and password exists or false if not. 
	 */
	boolean existsByNameAndPassword (String name , String password);
	/**
	 * This deleteCustomerByName delete a customer by given name from the database. 
	 * @param customer_name The name of the customer you want to delete from the database.
	 */
	@Transactional
	  @Modifying
	@Query("DELETE FROM CUSTOMER CUST WHERE CUST.name  = :customer_name")
	void deleteCustomerByName (@Param ("customer_name")String customer_name);
	/**
	 *<p>This getCouponsByType get collection of all Coupon objects with the given coupon type that have been purchased by the customer with the given id.</p>
	 * @param customer_id The id of the customer that purchased the coupons.
	 * @param couponType Enum of the coupons type you want to get from the database.
	 * @return Collection of all Coupon objects with the given coupon type that have been purchased by the customer with the given id
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.type = :coupon_type")
	Collection<Coupon> getCouponsByType (@Param ("customer_id")long customer_id , @Param("coupon_type")CouponType couponType);
	/**
	 *<p>This getCouponsByTopPrice get collection of all Coupon objects whose price is lower or equal to a given price and have been purchased by the customer with the given id.</p>
	 * @param customer_id The id of the customer that purchased the coupons.
	 * @param coupon_price The price limit of the coupons you want to get from the database.
	 * @return Collection of all Coupon objects whose price is lower or equal to a given price and have been purchased by the customer with the given id
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.price <= :coupon_price")
	Collection<Coupon> getCouponsByTopPrice (@Param ("customer_id")long customer_id , @Param("coupon_price")double price);
	/**
	 *<p>This getCouponByTitleAndCustomerId get Coupon object with the given coupon title that has been purchase by the customer with the given id.</p>
	 * @param customer_id The id of the customer that the coupon has been purchase by him.
	 * @param coupon_title The title of the coupon you want to get from the database.
	 * @return Coupon object with the given coupon title that the coupon has been purchase by him.
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.title = :coupon_title")
	Coupon getCouponByTitleAndCustomerId (@Param ("customer_id")long customer_id , @Param("coupon_title")String string);
}
