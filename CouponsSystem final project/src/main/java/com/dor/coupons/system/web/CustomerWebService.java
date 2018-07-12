package com.dor.coupons.system.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.dor.coupons.system.exceptions.CouponAlreadyPurchasedException;
import com.dor.coupons.system.exceptions.CouponExpierdException;
import com.dor.coupons.system.exceptions.CouponNotExistsException;
import com.dor.coupons.system.exceptions.CouponOutOfStockException;
import com.dor.coupons.system.facades.CustomerFacade;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
@RestController
@CrossOrigin("*")
public class CustomerWebService {
	private Logger logger = LogManager.getLogger(CustomerWebService.class);
	public CustomerWebService() {
		super ();
	}
	@RequestMapping(value = "/customer/purchase" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> purchaseCoupon (HttpServletRequest request, @RequestBody Coupon coupon)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			customerFacade.purchaseCoupon(coupon);
			logger.info("purchaseCoupon() worked successfully");
			return ResponseEntity.ok().body("Coupon has been purchased successfully");
		}
		catch (CouponAlreadyPurchasedException | CouponExpierdException | CouponOutOfStockException e)
		{
			logger.error("purchaseCoupon() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getPurchasedCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			Coupon coupon = customerFacade.getPurchasedCoupon(id);
			logger.info("getPurchasedCoupon() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getPurchasedCoupon() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getPurchasedCouponByTitle(HttpServletRequest request, @PathVariable ("title") String title)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			Coupon coupon = customerFacade.getPurchasedCouponByTitle(title);
			logger.info("getPurchasedCouponByTitle() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getPurchasedCouponByTitle() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/all" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllPurchasedCoupons(HttpServletRequest request)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		logger.info("getAllPurchasedCoupons() worked successfully");
		return customerFacade.getAllPurchasedCoupons();
	}
	@RequestMapping(value = "/customer/purchase/type/{type}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getPurchasedCouponsByType (HttpServletRequest request, @PathVariable ("type") String type)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		CouponType couponType = CouponType.valueOf(type);
		logger.info("getPurchasedCouponsByType() worked successfully");
		return customerFacade.getPurchasedCouponsByType(couponType);
	}
	@RequestMapping(value = "/customer/purchase/price/{price}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByTopPrice(HttpServletRequest request, @PathVariable double price)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		logger.info("getCouponsByTopPrice() worked successfully");
		return customerFacade.getPurchasedCouponsByPrice(price);
	}
	@RequestMapping(value = "/customer/coupon/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			Coupon coupon = customerFacade.getCoupon(id);
			logger.info("getCoupon() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getCoupon() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/coupon/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("title") String title)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			Coupon coupon = customerFacade.getCouponByTitle(title);
			logger.info("getCouponByTitle() worked successfully");
			return ResponseEntity.ok().body(coupon);
		}
		catch (CouponNotExistsException e)
		{
			logger.error("getCouponByTitle() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/coupon/all" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons(HttpServletRequest request)
	{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		logger.info("getAllCoupons() worked successfully");
		return customerFacade.getCouponsToBuy();
	}
}