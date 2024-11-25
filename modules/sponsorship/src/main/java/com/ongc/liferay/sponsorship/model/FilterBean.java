package com.ongc.liferay.sponsorship.model;
/**
 * @author Ranjeet
 */
public class FilterBean {
	private String publicationname;
	private String recommendedby;
	private String subject;
	private String purpose;
	private int subjectid;
	private String createdBy;
	private String updatedBy;
	private String role;
	private String refNo;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublicationname() {
		return publicationname;
	}

	public void setPublicationname(String publicationname) {
		this.publicationname = publicationname;
	}

	public String getRecommendedby() {
		return recommendedby;
	}

	public void setRecommendedby(String recommendedby) {
		this.recommendedby = recommendedby;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
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

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
}
