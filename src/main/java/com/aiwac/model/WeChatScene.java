package com.aiwac.model;

public class WeChatScene {
	int s_id;
	String name;
	int number;
	int available;
	String musicurl;

	
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	public String getMusicurl() {
		return musicurl;
	}
	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}
	@Override
	public String toString() {
		return "WeChatScene [s_id=" + s_id + ", name=" + name + ", number=" + number + ", available=" + available + "]";
	}

	
}
