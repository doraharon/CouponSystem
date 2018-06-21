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
import javax.persistence.OneToMany;


@Entity (name="COMPANY")
public class Company {
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
	
	public Company() {
		super();
	}

	public Company(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", coupons="
				+ coupons + "]";
	}
	public String myToString()
	{
		return "Company [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}
}
