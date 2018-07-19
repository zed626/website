package com.aiwac.bean.request;

import java.util.List;

import com.aiwac.model.WeChatScene;

public class WeChatSceneSum {
	private String businesscode;
	private List<WeChatScene> scenes;
	private int pagecount;
	private int pageindex;
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public List<WeChatScene> getScenes() {
		return scenes;
	}
	public void setScenes(List<WeChatScene> scenes) {
		this.scenes = scenes;
	}
	
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	@Override
	public String toString() {
		return "WeChatSceneSum [businesscode=" + businesscode + ", scenes=" + scenes + ", pagecount=" + pagecount
				+ ", pageindex=" + pageindex + ", getBusinesscode()=" + getBusinesscode() + ", getScenes()="
				+ getScenes() + ", getPagecount()=" + getPagecount() + ", getPageindex()=" + getPageindex()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
