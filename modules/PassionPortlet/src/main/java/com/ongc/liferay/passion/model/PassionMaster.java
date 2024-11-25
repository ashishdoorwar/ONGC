package com.ongc.liferay.passion.model;

import java.sql.Blob;
import java.util.Date;

public class PassionMaster {


	private String passionId;
	private String passionName;
	private String passionDescription;
	private String published;
	private String createdBy;
	private Date createdOn;
	private String fileName; 
	private Blob passionImage;
	public String getPassionId() {
		return passionId;
	}
	public void setPassionId(String passionId) {
		this.passionId = passionId;
	}
	public String getPassionName() {
		return passionName;
	}
	public void setPassionName(String passionName) {
		this.passionName = passionName;
	}
	public String getPassionDescription() {
		return passionDescription;
	}
	public void setPassionDescription(String passionDescription) {
		this.passionDescription = passionDescription;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
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
	public Blob getPassionImage() {
		return passionImage;
	}
	public void setPassionImage(Blob passionImage) {
		this.passionImage = passionImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
