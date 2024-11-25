package com.ongc.liferay.reports.model;

public class WorkplaceBean {

	
	private String workplace;
	private String name;
	private String location;
	private String address;
	private String department;
	private String mobile;
	private String officeNo;
	private String residenceNo;
	private String otherCities;
	private String subCategory;
	private String t1;
	private String t2;
	private String fax;
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getOtherCities() {
		return otherCities;
	}
	public void setOtherCities(String otherCities) {
		this.otherCities = otherCities;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Override
	public String toString() {
		return "WorkplaceBean [workplace=" + workplace + ", name=" + name + ", location=" + location + ", address="
				+ address + ", department=" + department + ", mobile=" + mobile + ", officeNo=" + officeNo
				+ ", residenceNo=" + residenceNo + ", otherCities=" + otherCities + ", subCategory=" + subCategory
				+ ", t1=" + t1 + ", t2=" + t2 + ", fax=" + fax + "]";
	}
	
	
}
