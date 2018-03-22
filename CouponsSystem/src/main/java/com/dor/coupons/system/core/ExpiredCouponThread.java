package com.dor.coupons.system.core;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.dor.coupons.system.dbdao.CouponDBDAO;
import com.dor.coupons.system.entities.SystemVariables;
/**
 * This ExpiredCouponThread will allow a thread that run when system goes up to delete all the expired coupons.
 * @author Dor aharon
 * @version 1.0
 * @category CORE class
 */
public class ExpiredCouponThread implements Runnable {
	private CouponDBDAO couponDBDAO;
	/**
	 * Default constractor for ExpiredCouponThread.
	 * @param ctx ApplicationContext to bring the couponDBDAO.class 
	 */
	public ExpiredCouponThread(ApplicationContext ctx) {
		super ();
	this.couponDBDAO = ctx.getBean(CouponDBDAO.class);
	}
	/**
	 * <p>The thread main code.</p>
	 * <p>This code run when the first login connected and runs every 24 hours.</p>
	 * <p>In shutdown mode the thread will go out from the loop and stop running</p>
	 */
	public void run()
	{
		while(!SynchronizePool.getInstance().isShuttingDown())
		{
			this.couponDBDAO.deleteExpiredCoupons(new Date());
		try {
			Thread.sleep(SystemVariables.day);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

}
