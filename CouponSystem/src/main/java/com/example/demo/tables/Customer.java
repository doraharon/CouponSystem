package com.example.demo.tables;

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
	public Customer() {
		super();
	}

	public Customer(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", coupons=" + coupons + "]";
	}
	public String myToString()
	{
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
