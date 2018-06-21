package com.example.demo.tables;





import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

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


@Entity (name = "COUPON")
public class Coupon {
	
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
	@Column (name="Image")
	private String image;
	@ManyToMany (fetch = FetchType.EAGER , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH })
	@JoinTable (name="Customer_Coupon",
	joinColumns = @JoinColumn (name = "Coupon_id"),
	inverseJoinColumns = @JoinColumn (name = "Customer_id"))
	Collection<Customer> customers;
	
	public Coupon() {
		super();
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}

	
	
}
