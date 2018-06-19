package com.dor.coupons.system.dbdao;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.Synchronize;
import com.dor.coupons.system.core.SynchronizePool;
import com.dor.coupons.system.dao.CouponDAO;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponRepo;
import com.dor.coupons.system.tables.CouponType;
/**
 *<p>This CouponDBDAO class implements CouponDAO interface,</p>
 *<p> and override all the methods for reading and writing to the Coupon table in the database.</p>
 *<p>All the methods that want to communicate with the database, get a Synchronize from the Synchronize pool and return it when finished.</p>
 * @author Dor aharon
 * @version 1.0
 * @category DBDAO class
 */
@Component
@Scope ("singleton")
public class CouponDBDAO implements CouponDAO{
	private SynchronizePool synchronizePool = SynchronizePool.getInstance();
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CompanyDBDAO companyDBDAO;
	/**
	 * Default constructor of CouponDBDAO.
	 */
		public CouponDBDAO() {
		super();
	}
		/**
		 *<p>{@inheritDoc}</p>
		 *<p> Use the couponRepo class to save the given coupon object to the database.</p>
		 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
		 */
	@Override
	public void createCoupon(Coupon coupon) {
		//TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		couponRepo.save(coupon);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the couponRepo class to delete the given coupon object by his title from the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void removeCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		couponRepo.deleteCouponByTitle(coupon.getTitle());
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the couponRepo class to update the given coupon object to the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void updateCoupon(Coupon coupon)  {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		couponRepo.save(coupon);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the couponRepo class to get a Coupon object by a given id</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Coupon getCoupon(long id) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Coupon coupon = couponRepo.findOne(id);
		synchronizePool.returnSynchronize(synchronize);
		return coupon;
	}
	/**
	 * <p>Use the couponRepo class to get a Coupon object by a given title.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param title The title of the coupon you want to get.
	 * @return Coupon object with the requested coupon title.
	 */
	public Coupon getCouponByTitle (String title)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Coupon coupon = couponRepo.findCouponByTitle(title);
		synchronizePool.returnSynchronize(synchronize);
		return coupon;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the couponRepo class to get all Coupons objects in the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Iterable<Coupon> getAllCoupons()  {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Iterable<Coupon> coupons = couponRepo.findAll();
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * {@inheritDoc}
	 * <p>Use the couponRepo class to get all coupons by given coupon type.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponType Enum of Coupon type of the requested coupons. 
	 * @return Collection of coupons objects with the requested coupon type.
	 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType couponType)  {	
	// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = couponRepo.findCouponByType(couponType);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use  the couponRepo to check if coupon has been purchased already by a customer given id.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p> 
	 * @param title Title of the coupon you want to check whether it was already purchased. 
	 * @param id Id of the customer trying to buy the coupon.
	 * @return Boolean whether the coupon has been purchased already or not.
	 */
	public boolean checkIfPurchasedCoupon (String title, long id)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		boolean checked = couponRepo.existsByTitleAndCustomers_Id(title, id);
		synchronizePool.returnSynchronize(synchronize);
		return checked;
	}
	/**
	 *<p>Use the couponRepo class to check if Coupon object by a given title exists in the database</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param title Title of the coupon you want to check if exists in the database.
	 * @return Boolean whether the coupon with the given title exists in the database.
	 */
	public Boolean checkCouponExistsBytitle(String title)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		boolean checked = couponRepo.existsByTitle(title);
		synchronizePool.returnSynchronize(synchronize);
		return checked;
	}
	public Collection<Coupon> getCouponsToBuy(Date date)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection <Coupon> coupons = couponRepo.getCoupons(date);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 *<p>This deleteExpiredCoupons use the couponRepo to delete all the coupons whose end date is before and same with given date.</p>
	 *<p>Mainly use in the daily thread to delete expired coupons</p>  
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param endDate The expiration date limit of the coupons you want to delete from the database.
	 */
	public void deleteExpiredCoupons (Date endDate)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		couponRepo.deleteCouponByEndDate(endDate);
		synchronizePool.returnSynchronize(synchronize);
	}
}
