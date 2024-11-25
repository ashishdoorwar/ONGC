package com.ongc.liferay.model;


import java.util.Date;

public class HolidayBean {

	private String holidayname;
	private String type;
	private Date hdate;
	private int dt;
	
	
	
	
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public String getHolidayname() {
		return holidayname;
	}
	public void setHolidayname(String holidayname) {
		this.holidayname = holidayname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getHdate() {
		return hdate;
	}
	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}
	
	
	
	
}
