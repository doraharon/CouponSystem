package com.dor.coupons.system.facades;

import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;

/**
 * <p>This CouponClientFacade is interface for the function login. </p>
 * <p>AdminFacade, CompanyFacade and CustomerFacade classes implements this interface.</p>
 * @author Dor aharon
 * @version 1.0
 * @category FACADE interface
 */
public interface CouponClientFacade {
	/**
	 * <p>login is for all clients who want to get in the CouponSystem will have to login to get premision to use the coupon system.</p>
	 * @param name Name of the client who wants to login to the system.
	 * @param password Password of the client who wants to login to the system. 
	 * @return CouponClientFacade successfully login will return AdminFacade, CompanyFacade or CustomerFacade according to the clientType login.
	 * @throws WrongUserOrPasswordException If client will try to login with wrong name or password. 
	 */
	CouponClientFacade login (String name , String password) throws WrongUserOrPasswordException;
}



