package com.aiwac.model;

public class WeChatPicture {
	int p_id;
	int s_id;
	int seriel;
	String url;
	int emotion;
	public int getEmotion() {
		return emotion;
	}
	public void setEmotion(int emotion) {
		this.emotion = emotion;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getSeriel() {
		return seriel;
	}
	public void setSeriel(int seriel) {
		this.seriel = seriel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "WeChatPicture [p_id=" + p_id + ", s_id=" + s_id + ", seriel=" + seriel + ", url=" + url + ", emotion="
				+ emotion + "]";
	}

	
}
