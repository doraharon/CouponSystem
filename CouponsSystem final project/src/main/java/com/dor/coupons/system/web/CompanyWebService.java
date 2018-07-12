package com.dor.coupons.system.web;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private Logger logger = LogManager.getLogger(CompanyWebService.class);
	public CompanyWebService() {
		super ();
	}
	@RequestMapping (value = "/company/coupon/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			Coupon coupon = companyFacade.getCoupon(id);
			logger.info("getCoupon() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getCompany() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCouponByTitle(HttpServletRequest request, @PathVariable ("title") String title)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			Coupon coupon = companyFacade.getCouponByTitle(title);
			logger.info("getCouponByTitle() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getCouponByTitle() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createCoupon(HttpServletRequest request, @RequestBody Coupon coupon)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		try
		{
			companyFacade.createCoupon(coupon);
			logger.info("createCoupon() worked successfully");
			return ResponseEntity.ok().body("Coupon has been added");
		}
		catch (CouponExistsException e) 
		{
			logger.error("createCoupon() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/company/coupon" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCoupon(HttpServletRequest request, @RequestBody Coupon coupon)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		companyFacade.updateCoupon(coupon);
		logger.info("updateCoupon() worked successfully");
		return "Coupon has been updated";
	}
	@RequestMapping (value = "/company/coupon/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		Coupon coupon = companyFacade.getCoupon(id);
		companyFacade.removeCoupon(coupon);
		logger.info("removeCoupon() worked successfully");
		return "Coupon has been removed";
	}
	@RequestMapping (value = "/company/coupons" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons (HttpServletRequest request)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		logger.info("getAllCoupons() worked successfully");
		return companyFacade.getAllCoupons();
	}
	@RequestMapping (value = "/company/coupons/type/{type}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection <Coupon> getAllCouponsByType(HttpServletRequest request, @PathVariable ("type") String type)
	{
		CouponType couponType = CouponType.valueOf(type);
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		logger.info("getAllCouponsByType() worked successfully");
		return companyFacade.getAllCouponsByType(couponType);
	}
	@RequestMapping (value = "/company/coupons/price/{price}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByTopPrice(HttpServletRequest request, @PathVariable ("price") double price)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		logger.info("getCouponsByTopPrice() worked successfully");
		return companyFacade.getCouponsByTopPrice(price);
	}
	@RequestMapping (value = "/company/coupons/date/{endDate}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByEndDate(HttpServletRequest request, @PathVariable ("endDate" )@DateTimeFormat(iso=ISO.DATE) Date endDate)
	{
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		logger.info("getCouponsByEndDate() worked successfully");
		return companyFacade.getCouponsByEndDate(endDate);
	}

}
