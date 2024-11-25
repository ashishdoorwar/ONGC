package com.ongc.liferay.model;

import java.util.Date;

public class SubPassion {
	private String passionId;
	private String subPassionId;
	private String passionName;
	private String subPassion;
	private String createdBy;
	private Date createdOn;
	public String getPassionId() {
		return passionId;
	}
	public void setPassionId(String passionId) {
		this.passionId = passionId;
	}
	public String getSubPassionId() {
		return subPassionId;
	}
	public void setSubPassionId(String subPassionId) {
		this.subPassionId = subPassionId;
	}
	public String getPassionName() {
		return passionName;
	}
	public void setPassionName(String passionName) {
		this.passionName = passionName;
	}
	public String getSubPassion() {
		return subPassion;
	}
	public void setSubPassion(String subPassion) {
		this.subPassion = subPassion;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "SubPassion [passionId=" + passionId + ", subPassionId=" + subPassionId + ", passionName=" + passionName
				+ ", subPassion=" + subPassion + ", createdBy=" + createdBy + ", createdOn=" + createdOn + "]";
	}
	
	
}
