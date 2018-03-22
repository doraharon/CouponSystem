package com.dor.coupons.system.dbdao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.Synchronize;
import com.dor.coupons.system.core.SynchronizePool;
import com.dor.coupons.system.dao.CustomerDAO;
import com.dor.coupons.system.exceptions.CustomerExistsException;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponRepo;
import com.dor.coupons.system.tables.CouponType;
import com.dor.coupons.system.tables.Customer;
import com.dor.coupons.system.tables.CustomerRepo;
/**
 *<p>This CustomerDBDAO class implements CustomerDAO interface,</p>
 *<p> and override all the methods for reading and writing to the Customer table in the database.</p>
 *<p>All the methods that want to communicate with the database, get a Synchronize from the Synchronize pool and return it when finished.</p>
 * @author Dor aharon
 * @version 1.0
 * @category DBDAO class
 */
@Component
@Scope ("singleton")
public class CustomerDBDAO implements CustomerDAO {
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	CouponRepo couponRepo;
	private SynchronizePool synchronizePool = SynchronizePool.getInstance();
	private long ConnectedId;
	/**
	 * Default constructor of CustomerDBDAO.
	 */
	public CustomerDBDAO() {
		super();
	}
	/**
	 *  Get the last customer who successfully logged in.
	 * @return Customer object of the last customer who successfully logged in.
	 */
	public long getConnectedId() {
		return ConnectedId;
	}
	/**
	 * Set the id of the last customer who successfully logged in. <b>(Not recommended to use)</b>
	 * @param connectedId Id of the last customer who successfully logged in.
	 */
	public void setConnectedId(long connectedId) {
		ConnectedId = connectedId;
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the customerRepo class to save the given customer object to the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void createCustomer(Customer customer) throws CustomerExistsException {
		// TODO Auto-generated method stub	
		Synchronize synchronize = synchronizePool.getSynchronize();
		customerRepo.save(customer);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the customerRepo class to delete the given customer object by name from the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		customerRepo.deleteCustomerByName(customer.getName());
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p> Use the customerRepo class to update the given customer object to the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		customerRepo.save(customer);
		synchronizePool.returnSynchronize(synchronize);
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the customerRepo class to get a Customer object by a given id</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Customer customer = customerRepo.findOne(id);
		synchronizePool.returnSynchronize(synchronize);
		return customer;
	}
	/**
	 *<p>Use the customerRepo class to check if Customer object by a given name exists in the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param name Name of the customer you want to check if exists in the database.
	 * @return Boolean whether the customer with the given name exists in the database.
	 */
	public boolean checkCustomerExistsByName (String name)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		boolean checked = customerRepo.existsByName(name);
		synchronizePool.returnSynchronize(synchronize);
		return checked;
	}
	/**
	 * <p>Use the customerRepo class to get a Customer object by a given name.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param name The name of the customer you want to get.
	 * @return Customer object with the given name.
	 */
	public Customer getCustomerByName (String name)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Customer customer = customerRepo.findCustomerByName(name);
		synchronizePool.returnSynchronize(synchronize);
		return customer;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the customerRepo class to get all Customers objects in the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Iterable<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		Iterable<Customer> customers = customerRepo.findAll();
		synchronizePool.returnSynchronize(synchronize);
		return customers;
	}
	/**
	 * <p>Get the last customer who successfully logged in.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @return Customer object of the last customer who successfully logged in.
	 */
	public Customer getConnectedCustomer ()
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Customer customer = customerRepo.findOne(this.ConnectedId);
		synchronizePool.returnSynchronize(synchronize);
		return customer;
	}
	/**
	 *<p>Use customerRepo to get a purchased Coupon by a given title.</p>
	 *<p>The coupon belongs to the last customer that successfully logged in.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param title Title of the purchesed coupon you want to get.
	 * @return Coupon object with the given title that has been purchased by the last successfully logged in customer.
	 */
	public Coupon getPurchasedCoupoonByTitle(String title)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Coupon coupon = customerRepo.getCouponByTitleAndCustomerId(ConnectedId, title);
		synchronizePool.returnSynchronize(synchronize);
		return coupon;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Getting the last customer who successfully logged in, and returning all its coupons.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stubget
		Synchronize synchronize = synchronizePool.getSynchronize();
		Customer customer = this.getConnectedCustomer();
		Collection<Coupon> coupons = customer.getCoupons();
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use the customerRepo class to get all coupons by given coupon type.</p>
	 * <p>The coupons belong to the last customer that successfully logged in.</p> 
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param couponType Enum of Coupon type of the requested coupons. 
	 * @return Collection of coupons objects with the requested coupon type.
	 */
	public Collection<Coupon> getCouponsByType (CouponType couponType)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = customerRepo.getCouponsByType(this.ConnectedId, couponType);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>Use the customerRepo class to get all coupons whose price is lower or equel to a given price.</p>
	 * <p>The coupons belong to the last customer that successfully logged in.</p> 
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 * @param price The maximum price (of the coupon) you want to get. 
	 * @return Collection of coupons objects whose price is lower or equal to the given price. 
	 */
	public Collection<Coupon> getCouponsByTopPrice (double price)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		Collection<Coupon> coupons = customerRepo.getCouponsByTopPrice(this.ConnectedId, price);
		synchronizePool.returnSynchronize(synchronize);
		return coupons;
	}
	/**
	 * <p>{@inheritDoc}</p>
	 * <p>Use the customerRepo class to check if there is a customer with the same given name and password in the database.</p>
	 * <p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 */
	@Override
	public boolean login(String customerName, String password) {
		// TODO Auto-generated method stub
		Synchronize synchronize = synchronizePool.getSynchronize();
		boolean connected = customerRepo.existsByNameAndPassword(customerName, password);
		if (connected)
		{
			this.ConnectedId = customerRepo.findCustomerByName(customerName).getId();
			synchronizePool.returnSynchronize(synchronize);
			return true;
		}
		synchronizePool.returnSynchronize(synchronize);
		return false;	
	}
}
