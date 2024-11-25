package com.ongc.liferay.reports.model;

import java.util.Date;

public class Domain {


	private int domainId;
	private String domainName;
	private String isActive;
	private Date createdOn;
	
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "Domain [domainId=" + domainId + ", domainName=" + domainName + ", isActive=" + isActive + "]";
	}
	
	
}
