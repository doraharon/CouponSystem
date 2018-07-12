package com.dor.coupons.system.dbdao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.Synchronize;
import com.dor.coupons.system.core.SynchronizePool;
import com.dor.coupons.system.tables.Transaction;
import com.dor.coupons.system.tables.TransactionRepo;
/**
 *<p>This TransactionDBDAO class is to save all the transactions that made at the website in transactiontable in the database.</p>
 *<p>All the methods that want to communicate with the database, get a Synchronize from the Synchronize pool and return it when finished.</p>
 * @author Dor aharon
 * @version 1.0
 * @category DBDAO class
 */
@Component
@Scope("prototype")
public class TransactionDBDAO {
	@Autowired
	TransactionRepo transactionsRepo;
	private SynchronizePool synchronizePool = SynchronizePool.getInstance();
	/**
	 * Default constructor of TransactionDBDAO.
	 */
	public TransactionDBDAO()
	{
		super ();
	}
	/**
	 *<p> Use the transactionRepo class to save the given company object to the database.</p>
	 *<p>This method get a Synchronize from the Synchronize pool and return it when finished.</p>
	 *@param transaction The transaction you want to save in the database.
	 */
	public void createTransaction(Transaction transaction)
	{
		Synchronize synchronize = synchronizePool.getSynchronize();
		transactionsRepo.save(transaction);
		synchronizePool.returnSynchronize(synchronize);
	}
}
