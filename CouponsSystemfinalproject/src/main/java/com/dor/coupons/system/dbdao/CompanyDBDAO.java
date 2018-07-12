package com.dor.coupons.system.dbdao;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.Synchronize;
import com.dor.coupons.system.core.SynchronizePool;
import com.dor.coupons.system.dao.CompanyDAO;
import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.CompanyRepo;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;

/**
 *<p>This CompanyDBDAO class implements CompanyDAO interface,</p>
 *<p>and override all the methods for reading and writing to the Company table in the database.</p>
 *<p>This class use the companyRepo class to write and read and write to the database.</p>
 *<p>All the methods that want to communicate with the database, get a Synchronize from the Synchronize pool and return it when finished.</p>
 * @author Dor aharon
 * @version 1.0
 * @category DBDAO class
 */
@Component
@Scope("prototype")
public class CompanyDBDAO implements CompanyDAO{
	@Autowired
	CompanyRepo companyRepo;
	private long connectedId;
	private SynchronizePool synchronizePool = SynchronizePool.getInstance();
	/**
	 * Default constructor of CompanyDBDAO.
	 */
	public CompanyDBDAO() {
		super();
	}
	/**
	 * Get the id of the last company who successfully logged in.
	 * @return long argument id of the last company who successfully logged in.
	 */
	public long getConnectedId() {
		return connectedId;
	}
	/**
	 * Set the id of the last company who successfully logged in. <b>(Not recommended to use)</b>
	 * @param connectedId Id of the last company who successfully logged in.
	 */
	public void setConnectedId(long connectedId) {
		this.connectedId = connectedId;
	}	
	/**
	 * <p>Get the last company who successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @return Company object of the last company who successfully logged in.
	 * @throws InterruptedException 
	 */
	public Company getConnectedCompany()  {
		Synchronize synchronize = synchronizePool.getSynchronize();
		Company connectedCompany = companyRepo.findOne(this.connectedId);
		synchronizePool.returnSynchronize(synchronize);
		return connectedCompany;
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the companyRepo class to save the given company object to the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void createCompany(Company company){
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		companyRepo.save(company);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the companyRepo class to delete the given company object from the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void removeCompany(Company company) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		companyRepo.delete(company);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the companyRepo class to update the given company object to the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		companyRepo.save(company);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the companyRepo class to get a Company object by a given id.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Company getCompany(long id) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Company company = companyRepo.findOne(id);
		synchronizePool.returnSynchronize(synchronize);
		return company;
	}
	/**
	 * <p>Use the companyRepo class to get a Company object by a given name.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param name The name of the company you want to get.
	 * @return Company object with the given name.
	 */
	public Company getCompanyByName (String name)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Company company = companyRepo.findCompanyByName(name);
		synchronizePool.returnSynchronize(synchronize);
		return company;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the companyRepo class to get all Companies objects in the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Iterable<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Iterable<Company> companies = companyRepo.findAll();
		synchronizePool.returnSynchronize(synchronize);
		return companies;
	}
	/**
	 * <p>Use the companyRepo class to get coupon by a given id.</p>
	 * <p>The coupon belongs to the last company that successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponId Id of the requested coupon. 
	 * @return Coupon object with the requested coupon id of the last company that successfully logged in.
	 */
	public Coupon getCouponById(long couponId)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Coupon coupon = companyRepo.getCouponByCompanyIdAndCouponId(connectedId, couponId);
		synchronizePool.returnSynchronize(synchronize);
		return coupon;
	}
	/**
	 * <p>Use the companyRepo class to get coupon by a given title.</p>
	 * <p>The coupon belongs to the last company that successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponTitle Title of the requested coupon. 
	 * @return Coupon object with the requested coupon title of the last company that successfully logged in.
	 */
	public Coupon getCouponByTitle(String couponTitle)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Coupon coupon = companyRepo.getCouponByCompanyIdAndCouponTitle(connectedId, couponTitle);
		synchronizePool.returnSynchronize(synchronize);
		return coupon;
	}
	/**
	 * <p>Use the companyRepo class to check if coupon exists in the database by a given title.</p> 
	 * <p>The coupon belongs to the last company that successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponTitle Title of the requested coupon. 
	 * @return Coupon object with the requested coupon title of the last company that successfully logged in.
	 */
	public boolean checkCompanyExistsByName (String name)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		boolean checked = companyRepo.existsByName(name);
		synchronizePool.returnSynchronize(synchronize);
		return checked;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Getting the last company who successfully logged in, and returning all its coupons.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Company company = this.getConnectedCompany();
		Collection<Coupon> coupons = company.getCoupons();
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use the companyRepo class to get all coupons by given coupon type.</p>
	 * <p>The coupons belong to the last company that successfully logged in.</p> 
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponType Enum of Coupon type of the requested coupons. 
	 * @return Collection of coupons objects with the requested coupon type.
	 */
	public Collection<Coupon> getCouponsByType (CouponType couponType)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = companyRepo.getCouponsByType(this.connectedId, couponType);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use the companyRepo class to get all coupons whose price is lower or equal to a given price.</p>
	 * <p>The coupons belong to the last company that successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p> 
	 * @param price The maximum price (of the coupon) you want to get. 
	 * @return Collection of coupons objects whose price is lower or equal to the given price. 
	 */
	public Collection<Coupon> getCouponsByTopPrice (double price)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = companyRepo.getCouponsByTopPrice(this.connectedId, price);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use the companyRepo class to get all coupons whose end date is before and same with given date.</p>
	 * <p>The coupons belong to the last company that successfully logged in.</p> 
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param endDate The last day of availability selected for coupons.
	 * @return Collection of coupons objects whose end date is before and same with given coupon end date.
	 */
	public Collection<Coupon> getCouponsByEndDate (Date endDate)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = companyRepo.getCouponsByEndDate(this.connectedId , endDate);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the companyRepo class to check if there is a company with the same given name and password in the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public boolean login(String companyName, String password) 
	{
		// TODO Auto-generated method stub
	Synchronize synchronize = synchronizePool.getSynchronize();
	boolean connected = companyRepo.existsByNameAndPassword(companyName, password);
	if (connected)
	{
		 this.connectedId = companyRepo.findCompanyByName(companyName).getId();
		 synchronizePool.returnSynchronize(synchronize);
		 return true;
	}
	synchronizePool.returnSynchronize(synchronize);
	return false;
	}
}
