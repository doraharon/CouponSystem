package com.dor.coupons.system.tables;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
/**
 * <p>Here Customer objects and table are created.</p>
 * <p>The Customer table is connected to the Coupon table by many to many</p>
 * @author Dor aharon
 * @version 1.0
 * @category TABLE class
 */
@Entity (name="CUSTOMER")
public class Customer {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name="Name")
	private String name;
	@Column (name="Password")
	private String password;
	@ManyToMany (fetch = FetchType.EAGER , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH })
	@JoinTable (name="Customer_Coupon",
	joinColumns = @JoinColumn (name = "Customer_id"),
	inverseJoinColumns = @JoinColumn (name = "Coupon_id"))
	private Collection<Coupon> coupons;
	/**
	 * Default constractor for Customer.
	 */
	public Customer() {
		super();
	}
	/**
	 * Main constractor for Customer.
	 * @param name The name of the customer.
	 * @param password The password of the customer.
	 */
	public Customer(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	/**
	 * This getId return the customer id.
	 * @return long argument of customer id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * This setId changes the customer's id. <b> Not recommended to use! </b>
	 * @param id The given id you want to replace with the current customer id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * This getName return the customer name.
	 * @return String argument of customer name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This setName changes the customer's name. <b> Not recommended to use! </b>
	 * @param name The given name you want to replace with the current customer name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This getPassword return the customer password.
	 * @return String argument of customer password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This setPassword changes the customer's password.
	 * @param password The given password you want to replace with the current customer password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * This getCoupons return the customer's purchased coupons.
	 * @return Collection of the customer's purchased Coupons objects.
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * This setCoupons changes all the customer's purchased coupons. <b> Not recommended to use! </b>
	 * @param coupon The given Collection of customer's purchased coupons you want to replace with the current customer coupons.
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * All the customer details formed to one long String.
	 * @return String with all the customer details.
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", coupons=" + coupons + "]";
	}
	/**
	 *<p> All the customer details except of the Collection of coupons formed to one long String.</p>
	 *<p> Mainly use when writing to the Transaction table </p>
	 * @return String with all the customer details except of the Collection of coupons.
	 */
	public String myToString()
	{
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
