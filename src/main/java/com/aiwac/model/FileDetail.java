package com.aiwac.model;

public class FileDetail {
	private int d_id;
	private String d_uuid;
	private String d_file_path;
	private int d_index;
	private String d_type;
	private String created_time;
	
	
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getD_uuid() {
		return d_uuid;
	}
	public void setD_uuid(String d_uuid) {
		this.d_uuid = d_uuid;
	}
	public String getD_file_path() {
		return d_file_path;
	}
	public void setD_file_path(String d_file_path) {
		this.d_file_path = d_file_path;
	}
	public int getD_index() {
		return d_index;
	}
	public void setD_index(int d_index) {
		this.d_index = d_index;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	@Override
	public String toString() {
		return "FileDetail [d_id=" + d_id + ", d_uuid=" + d_uuid + ", d_file_path=" + d_file_path + ", d_index="
				+ d_index + ", d_type=" + d_type + ", created_time=" + created_time + "]";
	}
}
