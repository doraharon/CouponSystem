package com.example.demo.tables;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.system.ClientType;

@Entity (name ="TRANSACTION")
public class Transaction {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name ="TimeStamp")
	private Timestamp timeStamp;
	@Enumerated (EnumType.STRING)
	@Column (name ="ClientType")
	private ClientType clientType;
	@Column (name ="ClientName")
	private String clientName;
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

	public Transaction(ClientType clientType, String clientName, ActionType actionType,
			ComponentType componentType, String componentDetails, boolean success, String exception) {
		super();
		this.timeStamp = new Timestamp(System.currentTimeMillis());
		this.clientType = clientType;
		this.clientName = clientName;
		this.actionType = actionType;
		this.componentType = componentType;
		this.componentDetails = componentDetails;
		this.success = success;
		this.exception = exception;
	}

	public long getId() {
		return id;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public String getComponentDetails() {
		return componentDetails;
	}

	public void setComponentDetails(String componentDetails) {
		this.componentDetails = componentDetails;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", timeStamp=" + timeStamp + ", clientType=" + clientType + ", clientName="
				+ clientName + ", actionType=" + actionType + ", componentType=" + componentType + ", componentDetails="
				+ componentDetails + ", success=" + success + ", exception=" + exception + "]";
	}
}
