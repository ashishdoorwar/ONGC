package com.ongc.liferay.model;


import java.io.File;
import java.util.Date;

public class OrderCircular {
	private String category;
	private String subject;
	private String issuedBy;
	private String orderDate;
	private String orderNumber;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private Date createDate;
	private int id;
	private String cretDate;
	
	
	public String getCretDate() {
		return cretDate;
	}

	public void setCretDate(String cretDate) {
		this.cretDate = cretDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderCircular [category=" + category + ", subject=" + subject + ", issuedBy=" + issuedBy
				+ ", orderDate=" + orderDate + ", orderNumber=" + orderNumber + ", fileUpload=" + fileUpload
				+ ", fileUploadContentType=" + fileUploadContentType + ", fileUploadFileName=" + fileUploadFileName
				+ ", createDate=" + createDate + ", id=" + id + ", cretDate=" + cretDate + "]";
	}
	
	
}