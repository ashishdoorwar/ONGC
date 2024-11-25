package com.ongc.liferay.passion.model;

import java.util.List;

public class HomeTrending {
	private String cpfNo;
	private String empName;
	private int endorseCount;
	private String passion;
	private String subPassion;
	private String passionId;
	private String subPassionId;
	private String fileId;
	private String fileName;
	private String caption;
	private String description;
	private String fileType;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	private int myPassionId;
	private List data;
	
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public String getCpfNo() {
		return cpfNo;
	}
	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEndorseCount() {
		return endorseCount;
	}
	public void setEndorseCount(int endorseCount) {
		this.endorseCount = endorseCount;
	}
	public String getPassion() {
		return passion;
	}
	public void setPassion(String passion) {
		this.passion = passion;
	}
	public String getSubPassion() {
		return subPassion;
	}
	public void setSubPassion(String subPassion) {
		this.subPassion = subPassion;
	}
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
	public int getMyPassionId() {
		return myPassionId;
	}
	public void setMyPassionId(int myPassionId) {
		this.myPassionId = myPassionId;
	}

}
