package com.dor.coupons.system.tables;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dor.coupons.system.core.ClientType;
/**
 * <p>Here Transactions and table are created.</p>
 * <p>Transaction is an action that made in the system and its save to the database in the transaction table.</p>
 * @author Dor aharon
 * @version 1.0
 * @category TABLE class
 */
@Entity (name ="TRANSACTION")
public class Transaction {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name ="TimeStamp")
	private Timestamp timeStamp;
	@Enumerated (EnumType.STRING)
	@Column (name ="ClientType")
	private ClientType clientType;
	@Column (name ="ClientDetails")
	private String clientDetails;
	@Enumerated (EnumType.STRING)
	@Column (name ="Action")
	private ActionType actionType;
	@Enumerated (EnumType.STRING)
	@Column (name ="Component")
	private ComponentType componentType;
	@Column (name ="ComponentDetails")
	private String componentDetails;
	@Column (name ="success")
	private boolean success;
	@Column (name ="Exception")
	private String exception;
	
	public Transaction() {
		super();
	}
	/**
	 * Main constractor for Transaction.
	 * @param clientType Enum  of the client type who did the action in the system. (Admin, Company, Customer)
	 * @param clientDetails The client Details who did the action in the system.
	 * @param actionType Enum of the action the client did in the system. (Login, Create, Update, Remove, Purchase)
	 * @param componentType Enum of the component type that the client did the action on. (Admin, Company, Customer, Coupon)
	 * @param componentDetails the Component Details that the client did the action on. 
	 * @param success Did the action succeed or not.
	 * @param exception The exception that made the action to fail. 
	 */
	public Transaction(ClientType clientType, String clientDetails, ActionType actionType,
			ComponentType componentType, String componentDetails, boolean success, String exception) {
		super();
		this.timeStamp = new Timestamp(System.currentTimeMillis());
		this.clientType = clientType;
		this.clientDetails = clientDetails;
		this.actionType = actionType;
		this.componentType = componentType;
		this.componentDetails = componentDetails;
		this.success = success;
		this.exception = exception;
	}
	/**
	 * This getId return the transaction id.
	 * @return long argument of transaction id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * This getException return the transaction exception.
	 * @return string argument of transaction exception that made the action to fail.
	 */
	public String getException() {
		return exception;
	}
	/**
	 * This setException changes the transaction exception . <b> Not recommended to use! </b>
	 * @param exception The given exception you want to replace with the current transaction exception details.
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
	/**
	 * This setId changes the transaction id. <b> Not recommended to use! </b>
	 * @param id The given id you want to replace with the current transaction id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * This getClientType return the transaction clientType.
	 * @return Enum of transaction clientType.
	 */
	public ClientType getClientType() {
		return clientType;
	}
	/**
	 * This setClientType changes the transaction clientType . <b> Not recommended to use! </b>
	 * @param clientType The given enum clientType you want to replace with the current transaction clientType.
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	/**
	 * This getClientDetails return the transaction clientDetails.
	 * @return string argument of transaction clientDetails.
	 */
	public String getClientDetails() {
		return clientDetails;
	}
	/**
	 * This setClientDetails changes the transaction clientDetails . <b> Not recommended to use! </b>
	 * @param clientDetails The given clientType you want to replace with the current transaction clientDetails.
	 */
	public void setClientDetails(String clientDetails) {
		this.clientDetails = clientDetails;
	}
	/**
	 * This getActionType return the transaction actionType.
	 * @return Enum of transaction actionType.
	 */
	public ActionType getActionType() {
		return actionType;
	}
	/**
	 * This setActionType changes the transaction actionType . <b> Not recommended to use! </b>
	 * @param actionType The given actionType you want to replace with the current transaction actionType.
	 */
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	/**
	 * This getComponentType return the transaction componentType.
	 * @return Enum of transaction componentType.
	 */
	public ComponentType getComponentType() {
		return componentType;
	}
	/**
	 * This setComponentType changes the transaction componentType . <b> Not recommended to use! </b>
	 * @param componentType The given componentType you want to replace with the current transaction componentType.
	 */
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	/**
	 * This getComponentDetails return the transaction componentDetails.
	 * @return string argument of transaction componentDetails.
	 */
	public String getComponentDetails() {
		return componentDetails;
	}
	/**
	 * This setComponentDetails changes the transaction componentDetails . <b> Not recommended to use! </b>
	 * @param componentDetails The given componentDetails you want to replace with the current transaction componentDetails.
	 */
	public void setComponentDetails(String componentDetails) {
		this.componentDetails = componentDetails;
	}
	/**
	 * This isSuccess return the transaction success status.
	 * @return boolean argument of transaction success status.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * This setSuccess changes the transaction success status . <b> Not recommended to use! </b>
	 * @param success The given success status you want to replace with the current transaction success status.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * All the transaction details formed to one long String.
	 * @return String with all the transaction details.
	 */
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", timeStamp=" + timeStamp + ", clientType=" + clientType + ", clientDetails="
				+ clientDetails + ", actionType=" + actionType + ", componentType=" + componentType + ", componentDetails="
				+ componentDetails + ", success=" + success + ", exception=" + exception + "]";
	}
}
