package com.dor.coupons.system.tables;
/**
* <p>ActionType Enum is used in Transaction class and table.</p>
* <p>ActionType is the action that the client did in the system.</p>
* <p>The action is made on one of the ComponentType (Admin, Company, Customer, Coupon, System).</p>
* @author Dor aharon
* @version 1.0
* @category TABLE enum
*/
public enum ActionType {
	LOGIN,
	CREATE,
	UPDATE,
	REMOVE,
	PURCHASE,
	SHUTDOWN
	

}
