package com.ongc.liferay.model;

import java.sql.Timestamp;
import java.util.Date;

public class SessionDto {
	private int id_key;
	private String session_id;	
	private String user_id;
	private Date login_time;
	private Timestamp logout_time;
	private String ip_address; 
	private String activeSessionTime; 
	 
	public int getId_key() {
		return id_key;
	}
	public void setId_key(int id_key) {
		this.id_key = id_key;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Timestamp getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Timestamp logout_time) {
		this.logout_time = logout_time;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getActiveSessionTime() {
		return activeSessionTime;
	}
	public void setActiveSessionTime(String activeSessionTime) {
		this.activeSessionTime = activeSessionTime;
	}
}
