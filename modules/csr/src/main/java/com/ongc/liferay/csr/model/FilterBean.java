package com.ongc.liferay.csr.model;

/**
 * @author Ranjeet
 */
public class FilterBean {

	private String subject;
	private String refNo;	
	private String programName;
	private String status;
	private String dateFrom;
	private String dateTo;
	private String createdBy;
	private String updatedBy;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Override
	public String toString() {
		return "FilterBean [subject=" + subject + ", refNo=" + refNo + ", programName=" + programName + ", status="
				+ status + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + "]";
	}
	
	
}
