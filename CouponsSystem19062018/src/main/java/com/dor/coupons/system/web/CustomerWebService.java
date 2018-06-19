package com.dor.coupons.system.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	CustomerFacade customerFacade;

	public CustomerWebService() {
		super ();
	}
	@RequestMapping(value = "/customer/purchase" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> purchaseCoupon (HttpServletRequest request, @RequestBody Coupon coupon)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			customerFacade.purchaseCoupon(coupon);
			return ResponseEntity.ok().body("Coupon has been purchased successfully");
		}
		catch (CouponAlreadyPurchasedException | CouponExpierdException | CouponOutOfStockException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getPurchasedCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			return ResponseEntity.ok().body(customerFacade.getPurchasedCoupon(id));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getPurchasedCouponByTitle(HttpServletRequest request, @PathVariable ("title") String title)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			return ResponseEntity.ok().body(customerFacade.getPurchasedCouponByTitle(title));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/purchase/all" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllPurchasedCoupons(HttpServletRequest request)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		return customerFacade.getAllPurchasedCoupons();
	}
	@RequestMapping(value = "/customer/purchase/type/{type}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getPurchasedCouponsByType (HttpServletRequest request, @PathVariable ("type") String type)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		CouponType couponType = CouponType.valueOf(type);
		return customerFacade.getPurchasedCouponsByType(couponType);
	}
	@RequestMapping(value = "/customer/purchase/price/{price}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsByTopPrice(HttpServletRequest request, @PathVariable double price)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		return customerFacade.getPurchasedCouponsByPrice(price);
	}
	@RequestMapping(value = "/customer/coupon/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			return ResponseEntity.ok().body(customerFacade.getCoupon(id));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/coupon/title/{title}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCoupon(HttpServletRequest request, @PathVariable ("title") String title)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		try
		{
			return ResponseEntity.ok().body(customerFacade.getCouponByTitle(title));
		}
		catch (CouponNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping(value = "/customer/coupon/all" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons(HttpServletRequest request)
	{
		//CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		return customerFacade.getCouponsToBuy();
	}
}
