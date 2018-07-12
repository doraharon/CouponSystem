package com.dor.coupons.system.core;

import java.util.ArrayList;


import org.springframework.stereotype.Component;

import com.dor.coupons.system.entities.ExceptionsMessages;
import com.dor.coupons.system.entities.SystemVariables;
import com.dor.coupons.system.exceptions.SystemClosedException;
/**
 * <p>This SynchronizePool is to make sure that only few clients in a time can communicate with the database. (according to the number of synchronizes.</p>
 * <p>Every action that will be made in the database will be have to get synchronize from the synchronizePool and return it when finished.</p>
 * @author Dor aharon
 * @version 1.0
 * @category CORE class
 */ 
@Component
public class SynchronizePool {
	
	private static SynchronizePool synchronizePool = null;
	private ArrayList<Synchronize> synchronizesPool;
	private boolean shuttingDown = false;
	
	private SynchronizePool ()
	{
		super ();
		this.synchronizesPool = new ArrayList<>();
		for (int i = SystemVariables.zero ; i < SystemVariables.numberOfSychronizes ; i++)
		{
			this.synchronizesPool.add(new Synchronize());			
		}
	}
	/**
	 * This isShuttingDown returns a boolean that shows if the system is in shutdown.
	 * @return Boolean of true if the system shutting down and false if not.
	 */
	public boolean isShuttingDown() {
		return shuttingDown;
	}
	/**
	 * <p>This getInstance is due Singelton pattern.</p>
	 * <p>Only from here the system can get or create a SynchronizePool.</p> 
	 * @return A new SynchronizePool object on the first entrance and from then on the same existing SynchronizePool.  
	 */
	public static SynchronizePool getInstance()
	{
		if (synchronizePool == null)
		{
			synchronizePool = new SynchronizePool();
		}
		return synchronizePool;
	}
	/**
	 * <p>Every method in the dbdao classes that want to communicate with the database will need to use this method to get a synchronize by turn.</p>
	 * <p>If there no Synchronize left the Thread will wait for one to return.</p>
	 * <p>If the system is in shutdown mode the SynchronizePool will be closed and exception will be thrown when asking for a Synchronize.</p> 
	 * @return Synchronize Object and with that the permission to communicate with the database.
	 */
	public synchronized Synchronize getSynchronize () 
	{
		if (shuttingDown)
		{
			throw new SystemClosedException(ExceptionsMessages.systemClosedExceptionMessage);
		}
		while (synchronizesPool.isEmpty())
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Synchronize synchronize = synchronizesPool.get(SystemVariables.zero);
		synchronizesPool.remove(synchronize);
		return synchronize;
	}
	/**
	 * <p>Every method in the dbdao classes that finish to communicate with the database will need to return the synchronize back to the SynchronizePool using this method.</p>
	 * <p>When a synchronize is return there a notify for threads who waiting for synchronize to return.</p>
	 * <p>If the system is in shutdown mode the SynchronizePool will be closed and every Synchronize that will come back will be sent to closeSynchronizePool for removing.
	 * @param synchronize The synchronize that you want to return to the synchronizePool when finished to communicate with the database.
	 */
	public synchronized void returnSynchronize(Synchronize synchronize)
	{		
		synchronizesPool.add(synchronize);
		if (shuttingDown)
		{
			this.closeSynchronizePool();
		}	
		notify();	
	}
	/**
	 * <p>This closeSynchronizePool will be called from the CouponSystem class when a shutdown request is committed.</p>
	 * <p>Here the SynchronizePool will be closed and all the Synchronizes will be removed.</p>
	 * @return Boolean of true if all the Synchronizes has been removed, and false if not.
	 */
	public boolean closeSynchronizePool()
	{
		this.shuttingDown = true;
		if (synchronizesPool.size() == SystemVariables.numberOfSychronizes)
		{
			this.synchronizesPool.clear();
			return true;
		}
			System.out.println(SystemVariables.shuttingDownNotification);
		try {
			Thread.sleep(SystemVariables.thirtySeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.synchronizesPool.clear();
		return false;
	}
}

