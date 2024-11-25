package com.ongc.liferay.reports.model;

public class OrganizationBean {

	private String subsiDaryId;
	private String name;
	private String designation;
	private String location;
	private String officeNo;
	private String residenceNo;
	private String mobile;
	private String fax;
	private String address;
	private String email;
	private String department;
	private String t1;
	private String t2;
	private String squence;
	public String getSubsiDaryId() {
		return subsiDaryId;
	}
	public void setSubsiDaryId(String subsiDaryId) {
		this.subsiDaryId = subsiDaryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public String getSquence() {
		return squence;
	}
	public void setSquence(String squence) {
		this.squence = squence;
	}
	@Override
	public String toString() {
		return "OrganizationBean [subsiDaryId=" + subsiDaryId + ", name=" + name + ", designation=" + designation
				+ ", location=" + location + ", officeNo=" + officeNo + ", residenceNo=" + residenceNo + ", mobile="
				+ mobile + ", fax=" + fax + ", address=" + address + ", email=" + email + ", department=" + department
				+ ", t1=" + t1 + ", t2=" + t2 + ", squence=" + squence + "]";
	}
	
	
}
