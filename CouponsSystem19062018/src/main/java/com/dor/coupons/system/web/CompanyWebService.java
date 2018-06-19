package com.dor.coupons.system.web;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dor.coupons.system.exceptions.CouponExistsException;
import com.dor.coupons.system.exceptions.CouponNotExistsException;
import com.dor.coupons.system.facades.CompanyFacade;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
@RestController
@CrossOrigin("*")
public class CompanyWebService {
	@Autowired
	CompanyFacade companyFacade;
	public CompanyWebService() {
		super ();
	}
	@RequestMapping (value = "/company/coupon/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			return ResponseEntity.ok().body(companyFacade.getCoupon(id));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCouponByTitle(HttpServletRequest request, @PathVariable ("title") String title)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			return ResponseEntity.ok().body(companyFacade.getCouponByTitle(title));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createCoupon(HttpServletRequest request, @RequestBody Coupon coupon)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			companyFacade.createCoupon(coupon);
			return ResponseEntity.ok().body("Coupon has been added");
		}
		catch (CouponExistsException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCoupon(HttpServletRequest request, @RequestBody Coupon coupon)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		companyFacade.updateCoupon(coupon);
		return "Coupon has been updated";
	}
	@RequestMapping (value = "/company/coupon/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		Coupon coupon = companyFacade.getCoupon(id);
		companyFacade.removeCoupon(coupon);
		return "Coupon has been removed";
	}
	@RequestMapping (value = "/company/coupons" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons (HttpServletRequest request)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		return companyFacade.getAllCoupons();
	}
	@RequestMapping (value = "/company/coupons/type/{type}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection <Coupon> getAllCouponsByType(HttpServletRequest request, @PathVariable ("type") String type)
	{
		CouponType couponType = CouponType.valueOf(type);
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		return companyFacade.getAllCouponsByType(couponType);
	}
	@RequestMapping (value = "/company/coupons/price/{price}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByTopPrice(HttpServletRequest request, @PathVariable ("price") double price)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		return companyFacade.getCouponsByTopPrice(price);
	}
	@RequestMapping (value = "/company/coupons/date/{endDate}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByEndDate(HttpServletRequest request, @PathVariable ("endDate" )@DateTimeFormat(iso=ISO.DATE) Date endDate)
	{
		//CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		return companyFacade.getCouponsByEndDate(endDate);
	}

}
