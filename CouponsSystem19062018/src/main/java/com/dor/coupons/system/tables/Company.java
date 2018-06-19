package com.dor.coupons.system.tables;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/**
 * <p>Here Company objects and table are created.</p>
 * <p>The Company table is connected to the Coupon table by one to many</p>
 * @author Dor aharon
 * @version 1.0
 * @category TABLE class
 */
@Entity (name="COMPANY")
public class Company implements Serializable{
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name="Name")
	private String name;
	@Column (name="Password")
	private String password;
	@Column (name="Email")
	private String email;
	@OneToMany (fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn (name="Company_id")
	private Collection<Coupon> coupons;
	/**
	 * Default constractor for Company.
	 */
	public Company() {
		super();
	}
	/**
	 * Main constractor for Company.
	 * @param name The company name.
	 * @param password The company password.
	 * @param email The company email.
	 */
	public Company(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	/**
	 * This getId return the company id.
	 * @return long argument of company id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * This setId changes the company's id. <b> Not recommended to use! </b>
	 * @param id The given id you want to replace with the current company id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * This getName return the company name.
	 * @return String argument of company name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This setName changes the company's name. <b> Not recommended to use! </b>
	 * @param name The given name you want to replace with the current company name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This getPassword return the company password.
	 * @return String argument of company password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This setPassword changes the company's password.
	 * @param password The given password you want to replace with the current company password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * This getEmail return the company email.
	 * @return String argument of company email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * This setEmail changes the company's email.
	 * @param email The given email you want to replace with the current company email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * This getCoupons return the company's coupons.
	 * @return Collection of the company's Coupons objects.
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * This setCoupons changes all the company's coupons. <b> Not recommended to use! </b>
	 * @param coupon The given Collection of coupons you want to replace with the current company coupons.
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * All the company details formed to one long String.
	 * @return String with all the company details.
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", coupons="
				+ coupons + "]";
	}
	/**
	 *<p> All the company details except of the Collection of coupons formed to one long String.</p>
	 *<p> Mainly use when writing to the Transaction table </p>
	 * @return String with all the company details except of the Collection of coupons.
	 */
	public String myToString()
	{
		return "Company [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}
}
