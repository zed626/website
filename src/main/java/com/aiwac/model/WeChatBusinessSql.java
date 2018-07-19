package com.aiwac.model;

public class WeChatBusinessSql {
	int id;
	String business_id;
	String u_id;
	int s_id;
	String photopath;
	int score;
	String platform;
	int seriel;
	int realemotion;
	int expectemotion;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}



	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getSeriel() {
		return seriel;
	}

	public void setSeriel(int seriel) {
		this.seriel = seriel;
	}

	public int getRealemotion() {
		return realemotion;
	}

	public void setRealemotion(int realemotion) {
		this.realemotion = realemotion;
	}

	public int getExpectemotion() {
		return expectemotion;
	}

	public void setExpectemotion(int expectemotion) {
		this.expectemotion = expectemotion;
	}

	@Override
	public String toString() {
		return "WeChatBusiness [idString=" + id + ", u_id=" + u_id + ", s_id=" + s_id + ", photopath=" + photopath
				+ ", score=" + score + ", platform=" + platform + ", seriel=" + seriel + ", realemotion=" + realemotion
				+ ", expectemotion=" + expectemotion + "]";
	}

	
}
