package com.ongc.liferay.model;

import java.util.Date;

public class Passion
{
	private String passionId;
	private String passionName;
	private String passionDescription;
	private String published;
	private String createdBy;
	private Date createdOn;
	private String cpfNo;
	private String subPassionId;
	private String comments;
	private String subPassion;
	private String psnMPID;

	public String getPassionId()
	{
		return this.passionId;
	}
	public void setPassionId(String passionId) {
		this.passionId = passionId;
	}
	public String getPassionName() {
		return this.passionName;
	}
	public void setPassionName(String passionName) {
		this.passionName = passionName;
	}
	public String getPassionDescription() {
		return this.passionDescription;
	}
	public void setPassionDescription(String passionDescription) {
		this.passionDescription = passionDescription;
	}
	public String getPublished() {
		return this.published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return this.createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCpfNo() {
		return this.cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getSubPassionId() {
		return this.subPassionId;
	}
	public void setSubPassionId(String subPassionId) {
		this.subPassionId = subPassionId;
	}
	public String getComments() {
		return this.comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSubPassion() {
		return this.subPassion;
	}
	public void setSubPassion(String subPassion) {
		this.subPassion = subPassion;
	}
	public String getPsnMPID() {
		return psnMPID;
	}
	public void setPsnMPID(String psnMPID) {
		this.psnMPID = psnMPID;
	}
	@Override
	public String toString() {
		return "Passion [passionId=" + passionId + ", passionName=" + passionName + ", passionDescription="
				+ passionDescription + ", published=" + published + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", cpfNo=" + cpfNo + ", subPassionId=" + subPassionId + ", comments=" + comments
				+ ", subPassion=" + subPassion + ", psnMPID=" + psnMPID + "]";
	}
	
	
}