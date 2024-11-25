package com.ongc.liferay.reports.model;

public class FeedbackHrEnablers {

	private String cpfNo;
	private String role;
	private int hrCatId;
	private String hrLocation;
	private String subLocation;
	
	public String getSubLocation() {
		return subLocation;
	}
	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}
	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getHrCatId() {
		return hrCatId;
	}
	public void setHrCatId(int hrCatId) {
		this.hrCatId = hrCatId;
	}
	public String getHrLocation() {
		return hrLocation;
	}
	public void setHrLocation(String hrLocation) {
		this.hrLocation = hrLocation;
	}
	
	@Override
	public String toString() {
		return "FeedbackHrEnablers [cpfNo=" + cpfNo + ", role=" + role + ", hrCatId=" + hrCatId + ", hrLocation="
				+ hrLocation + ", subLocation=" + subLocation + "]";
	}
	
	
}
