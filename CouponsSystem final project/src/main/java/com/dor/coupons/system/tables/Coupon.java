package com.dor.coupons.system.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * <p>Here Coupon objects and table are created.</p>
 * <p>The Coupon table is connected to the Customer table by many to many.</p>
 * @author Dor aharon
 * @version 1.0
 * @category TABLE class
 */
@Entity (name = "COUPON")
public class Coupon implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name="Title")
	private String title;
	@Column (name="Start_Date")
	private Date startDate;
	@Column (name="End_Date")
	private Date endDate;
	@Column (name="Amount")
	private Integer amount;
	@Column (name="Type")
	@Enumerated (EnumType.STRING)
	private CouponType type;
	@Column (name="Message")
	private String message;
	@Column (name="Price")
	private double price;
	@Column (name="Image", length = 1048)
	private String image;
	@ManyToMany (fetch = FetchType.EAGER , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH })
	@JoinTable (name="Customer_Coupon",
	joinColumns = @JoinColumn (name = "Coupon_id"),
	inverseJoinColumns = @JoinColumn (name = "Customer_id"))
	Collection<Customer> customers;
	/**
	 * Default constractor for Coupon.
	 */
	public Coupon() {
		super();
		this.startDate = new Date ();
	}
	/**
	 * <p>Main constractor for Coupon.</p>
	 * <p>Start date will automatically get the date of today</p>
	 * @param title The title of the coupon.
	 * @param endDate The expiration date of the coupon.
	 * @param amount The amount of coupons in stock.
	 * @param type The type of the coupon (enum).
	 * @param message A free description of the coupon.
	 * @param price The price that the coupon cost.
	 * @param image An image for the coupon.
	 */
	public Coupon(String title, Date endDate, Integer amount, CouponType type, String message,
			double price, String image) {
		super();
		this.title = title;
		this.startDate = new Date ();
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	/**
	 * This getId return the coupon id.
	 * @return long argument of Coupon id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * This setId changes the Coupon's id. <b> Not recommended to use! </b>
	 * @param id The given id you want to replace with the current coupon id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * This getTitle return the coupon title.
	 * @return String argument of Coupon title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * This setTitle changes the coupon's title. <b> Not recommended to use! </b>
	 * @param title The given title you want to replace with the current coupon name.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * This getStartDate return the date when the coupon has been created.
	 * @return Date object of when the coupon has been created.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * This setStartDate changes the coupon creation date. <b> Not recommended to use! </b>
	 * @param startDate the new creation date given for the coupon.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * This getEndDate return the coupon expiration date.
	 * @return Date object of Coupon expiration date.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * This setEndDate changes the coupon expiration date.
	 * @param endDate the new expiration date given for the coupon.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * This getAmount return how many coupons there in stock.
	 * @return Integer object of the coupon amount.
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * This setAmount changes how many coupons there in stock.
	 * @param amount The given coupon amount you want to replace in the database.
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * This getType return the coupon type.
	 * @return CouponType (enum) of the coupon type.
	 */
	public CouponType getType() {
		return type;
	}
	/**
	 * This setType changes the type of the coupon.
	 * @param type The given coupon type you want to replace in the database.
	 */
	public void setType(CouponType type) {
		this.type = type;
	}
	/**
	 * This getMessage return the description of the coupon.
	 * @return String argument of the description of the coupon.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * This setMessage changes the description of the coupon.
	 * @param message The given coupon description you want to replace in the database.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * This getPrice return the price of the coupon.
	 * @return double argument of the price of the coupon.
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * This setPrice changes the price of the coupon.
	 * @param price The given coupon price you want to replace in the database.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * This getImage return the image of the coupon.
	 * @return String argument of the image of the coupon.
	 */
	public String getImage() {
		return image;
	}
	/**
	 * This setImage changes the image of the coupon.
	 * @param image The given coupon image you want to replace in the database.
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * All the Coupon details formed to one long String.
	 * @return String with all the Company details.
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
}
