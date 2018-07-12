package com.dor.coupons.system.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.entities.SystemVariables;
import com.dor.coupons.system.facades.AdminFacade;
import com.dor.coupons.system.facades.CompanyFacade;
import com.dor.coupons.system.facades.CouponClientFacade;
import com.dor.coupons.system.facades.CustomerFacade;
/**
 * <p>The gate to the system.</p>
 * <p>All the clients that want to login to the system will have to pass throw here.</p>
 * @author Dor aharon
 * @version 1.0
 * @category CORE class
 */
@Component
@Scope ("singleton")
public class CouponSystem  {
	@Autowired
	ApplicationContext ctx;
	private boolean loggedin = true;
	/**
	 * Default constractor for CouponSystem.
	 */
	public CouponSystem () {
		super ();
	}
	/**
	 *<p>This login is where all the clients (Admin, Company, Customer) have to pass throw here to log to the system.</p>
	 *<p>This login create Facade according to the client type given.</p>
	 *<p>The first login will run a thread that will delete all the expired coupons every day.</p>
	 * @param name client name of the client who wants to login to the system.
	 * @param password client password of the client who wants to login to the system.
	 * @param clientType The client type (Admin, Company, Customer) of the user who wants to login
	 * @return CouponClientFacade interface will be returned as a facade (according to the client type) if the login was successful 
	 */
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		if(this.loggedin)
		{
			ExpiredCouponThread deleteExpiredCoupons = new ExpiredCouponThread(ctx);
			Thread expiredCouponDeleter = new Thread(deleteExpiredCoupons);
			expiredCouponDeleter.start();
			this.loggedin=false;
		}
		switch (clientType)
		{
		case ADMIN:
			AdminFacade adminFacade = ctx.getBean(AdminFacade.class);
			return adminFacade.login(name, password);
		case COMPANY:
			CompanyFacade companyFacade = ctx.getBean(CompanyFacade.class);
			return companyFacade.login(name, password);
		case CUSTOMER:
			CustomerFacade customerFacade = ctx.getBean(CustomerFacade.class);
			return customerFacade.login(name, password);
		default: return null;
		}
	}
	/**
	 *<p>This shutdown close the synchronize pool and if all the synchronize deleted exit regularly else exit with an error.<p>
	 */
		public void shutdown ()
		{
			boolean quit = SynchronizePool.getInstance().closeSynchronizePool();
			if (quit)
			{
				System.exit(SystemVariables.zero);
			}
			System.exit(SystemVariables.one);
		}	
}
