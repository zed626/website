package com.aiwac.bean.request;

public class WeChatBusiness {
	private String data;
	private String business_id;
	private String user_id;
	private int scene_id;
	private int seriel;
	private int label;
	private String platform;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getScene_id() {
		return scene_id;
	}
	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}
	public int getSeriel() {
		return seriel;
	}
	public void setSeriel(int seriel) {
		this.seriel = seriel;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	@Override
	public String toString() {
		return "WechatPic [data=" + data + ", business_id=" + business_id + ", user_id=" + user_id + ", scene_id="
				+ scene_id + ", seriel=" + seriel + ", label=" + label + ", platform=" + platform + ", getData()="
				+ getData() + ", getBusiness_id()=" + getBusiness_id() + ", getUser_id()=" + getUser_id()
				+ ", getScene_id()=" + getScene_id() + ", getSeriel()=" + getSeriel() + ", getLabel()=" + getLabel()
				+ ", getPlatform()=" + getPlatform() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public WeChatBusiness(String data, String business_id, String user_id, int scene_id, int seriel, int label,
			String platform) {
		super();
		this.data = data;
		this.business_id = business_id;
		this.user_id = user_id;
		this.scene_id = scene_id;
		this.seriel = seriel;
		this.label = label;
		this.platform = platform;
	}
	public WeChatBusiness() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
