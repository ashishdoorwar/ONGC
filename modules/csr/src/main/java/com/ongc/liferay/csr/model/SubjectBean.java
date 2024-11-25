package com.ongc.liferay.csr.model;

import java.sql.Timestamp;



/**
 * @author Ranjeet
 */
public class SubjectBean {

	private int subId;
	private String subName;
	private Timestamp createdOn;
	private String createdBy;
	
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "SubjectBean [subId=" + subId + ", subName=" + subName + ", createdOn=" + createdOn + ", createdBy="
				+ createdBy + "]";
	}
	
	

	

}
