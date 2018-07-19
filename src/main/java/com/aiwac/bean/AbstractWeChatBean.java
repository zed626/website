package com.aiwac.bean;

/**
*
* @author HC
* @date 2017年11月10日
*
*/

public abstract class AbstractWeChatBean extends AbstractBean {
	protected String clientID;
	protected String businessType;
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
	
	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public AbstractWeChatBean() {
		super();
	}

	public AbstractWeChatBean(String clientID, String businessType, String uniqueID) {
		super();
		this.clientID = clientID;
		this.businessType = businessType;
		this.uniqueID = uniqueID;		
		
	}
}
