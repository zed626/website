package com.aiwac.bean;

/**
*
* @author HC
* @date 2017年11月10日
*
*/

public abstract class AbstractSocketBean extends AbstractBean {
	protected String clientID;
	protected String businessType;
	protected String clientType;
	protected String uniqueID;
	

	public String getClientID() {
		return clientID;
	}
	
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getBusinessType() {
		return businessType;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public String getClientType() {
		return clientType;
	}
	
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public AbstractSocketBean() {
		super();
	}

	public AbstractSocketBean(String clientID, String businessType, String clientType, String uniqueID) {
		super();
		this.clientID = clientID;
		this.businessType = businessType;
		this.clientType = clientType;
		this.uniqueID = uniqueID;		
		
	}
}
