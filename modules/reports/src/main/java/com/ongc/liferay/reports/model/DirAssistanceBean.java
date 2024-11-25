package com.ongc.liferay.reports.model;

public class DirAssistanceBean {

	private String cpfNo;
	private String name;
	private String location;
	private String infocomUnit;
	private String designation;
	private String category;
	private String officeNo;
	private String residenceNo;
	private String mobile;
	private String email;
	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInfocomUnit() {
		return infocomUnit;
	}
	public void setInfocomUnit(String infocomUnit) {
		this.infocomUnit = infocomUnit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	public String getResidenceNo() {
		return residenceNo;
	}
	public void setResidenceNo(String residenceNo) {
		this.residenceNo = residenceNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "DirAssistanceBean [cpfNo=" + cpfNo + ", name=" + name + ", location=" + location + ", infocomUnit="
				+ infocomUnit + ", designation=" + designation + ", category=" + category + ", officeNo=" + officeNo
				+ ", residenceNo=" + residenceNo + ", mobile=" + mobile + ", email=" + email + "]";
	}
	
	
}
