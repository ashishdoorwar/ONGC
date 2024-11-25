package com.ongc.liferay.sponsorship.model;

import java.io.File;
/**
 * @author Ranjeet
 */
public class SponsorshipBean {

	private int sponid;
	private String emailid;
	private String publicationname;
	private String recommendedby;
	private String subject;
	private String refno;
	private String status;
	private String proposalval;
	private String offeredval;
	private String deliverable;
	private String createdon;
	private String updatedon;
	private String purpose;
	private String expenditure;
	private String otherStatus;
	private String approvalAuth;
	private String ronum;
	private String rodt;
	private String eventDate;
	private String receivedDate;
	private String filenumber;

	/*********************************************************
	 * Add field on per requirement given by Ratan Shrey ongc Date : 20/05/2020
	 *********************************************************/
	private String frNumber;
	private String trackingNumber;

	/*********************************************************
	 * Add field on per requirement given by Ratan Shrey ongc Date : 06/07/2020
	 *********************************************************/
	private String mobileNo;
	private String internalRecomendation;
	private String externalRecomendation;
	private File proposalDoc;
	private String proposalDocContentType;
	private String proposalDocFileName;
	

	private File releaseOrderDoc;
	private String releaseOrderDocContentType;
	private String releaseOrderDocFileName;
	
	private String createdBy;
	private String updatedBy;
	
	
	private String sponsType;
	private String sponsTypeOther;

	/*******************************************************************************
	 * Add field on per requirement given by Ratan Shrey ongc Date : 03/09/2020
	 *******************************************************************************/
	private String dealingOfficer;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String string) {
		this.createdon = string;
	}

	public String getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(String string) {
		this.updatedon = string;
	}

	public int getSponid() {
		return sponid;
	}

	public void setSponid(int sponid) {
		this.sponid = sponid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getProposalval() {
		return proposalval;
	}

	public void setProposalval(String proposalval) {
		this.proposalval = proposalval;
	}

	public String getOfferedval() {
		return offeredval;
	}

	public void setOfferedval(String offeredval) {
		this.offeredval = offeredval;
	}

	public String getDeliverable() {
		return deliverable;
	}

	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public String getOtherStatus() {
		return otherStatus;
	}

	public void setOtherStatus(String otherStatus) {
		this.otherStatus = otherStatus;
	}

	public String getApprovalAuth() {
		return approvalAuth;
	}

	public void setApprovalAuth(String approvalAuth) {
		this.approvalAuth = approvalAuth;
	}

	public String getRonum() {
		return ronum;
	}

	public void setRonum(String ronum) {
		this.ronum = ronum;
	}

	public String getRodt() {
		return rodt;
	}

	public void setRodt(String rodt) {
		this.rodt = rodt;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(String filenumber) {
		this.filenumber = filenumber;
	}

	public String getFrNumber() {
		return frNumber;
	}

	public void setFrNumber(String frNumber) {
		this.frNumber = frNumber;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public File getProposalDoc() {
		return proposalDoc;
	}

	public void setProposalDoc(File proposalDoc) {
		this.proposalDoc = proposalDoc;
	}

	public String getProposalDocContentType() {
		return proposalDocContentType;
	}

	public void setProposalDocContentType(String proposalDocContentType) {
		this.proposalDocContentType = proposalDocContentType;
	}

	public String getProposalDocFileName() {
		return proposalDocFileName;
	}

	public void setProposalDocFileName(String proposalDocFileName) {
		this.proposalDocFileName = proposalDocFileName;
	}

	public File getReleaseOrderDoc() {
		return releaseOrderDoc;
	}

	public void setReleaseOrderDoc(File releaseOrderDoc) {
		this.releaseOrderDoc = releaseOrderDoc;
	}

	public String getReleaseOrderDocContentType() {
		return releaseOrderDocContentType;
	}

	public void setReleaseOrderDocContentType(String releaseOrderDocContentType) {
		this.releaseOrderDocContentType = releaseOrderDocContentType;
	}

	public String getReleaseOrderDocFileName() {
		return releaseOrderDocFileName;
	}

	public void setReleaseOrderDocFileName(String releaseOrderDocFileName) {
		this.releaseOrderDocFileName = releaseOrderDocFileName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getInternalRecomendation() {
		return internalRecomendation;
	}

	public void setInternalRecomendation(String internalRecomendation) {
		this.internalRecomendation = internalRecomendation;
	}

	public String getExternalRecomendation() {
		return externalRecomendation;
	}

	public void setExternalRecomendation(String externalRecomendation) {
		this.externalRecomendation = externalRecomendation;
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
	
	public void setSponsType(String sponsType) {
		this.sponsType = sponsType;
	}
	
	public String getSponsType() {
		return sponsType;
	}
	public void setSponsTypeOther(String sponsTypeOther) {
		this.sponsTypeOther = sponsTypeOther;
	}
	
	public String getSponsTypeOther() {
		return sponsTypeOther;
	}
	
	public void setDealingOfficer(String dealingOfficer) {
		this.dealingOfficer = dealingOfficer;
	}
	public String getDealingOfficer() {
		return dealingOfficer;
	}

	@Override
	public String toString() {
		return "SponsorshipBean [sponid=" + sponid + ", emailid=" + emailid + ", publicationname=" + publicationname
				+ ", recommendedby=" + recommendedby + ", subject=" + subject + ", refno=" + refno + ", status="
				+ status + ", proposalval=" + proposalval + ", offeredval=" + offeredval + ", deliverable="
				+ deliverable + ", createdon=" + createdon + ", updatedon=" + updatedon + ", purpose=" + purpose
				+ ", expenditure=" + expenditure + ", otherStatus=" + otherStatus + ", approvalAuth=" + approvalAuth
				+ ", ronum=" + ronum + ", rodt=" + rodt + ", eventDate=" + eventDate + ", receivedDate=" + receivedDate
				+ ", filenumber=" + filenumber + ", frNumber=" + frNumber + ", trackingNumber=" + trackingNumber
				+ ", mobileNo=" + mobileNo + ", internalRecomendation=" + internalRecomendation
				+ ", externalRecomendation=" + externalRecomendation + ", proposalDoc=" + proposalDoc
				+ ", proposalDocContentType=" + proposalDocContentType + ", proposalDocFileName=" + proposalDocFileName
				+ ", releaseOrderDoc=" + releaseOrderDoc + ", releaseOrderDocContentType=" + releaseOrderDocContentType
				+ ", releaseOrderDocFileName=" + releaseOrderDocFileName + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", sponsType=" + sponsType + ", sponsTypeOther=" + sponsTypeOther + ", dealingOfficer="
				+ dealingOfficer + "]";
	}

	
}
